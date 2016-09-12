package cn.mapway.doc2.parser;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.resource.Scans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.mapway.doc2.meta.ApiDoc;
import cn.mapway.doc2.meta.Entry;
import cn.mapway.doc2.meta.Group;
import cn.mapway.doc2.meta.ObjectInfo;
import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;
import cn.mapway.document.gen.module.GenContext;

/**
 * 解析Spring 注解
 * 
 * @author zhangjianshe@gmail.com
 *
 */
public class SpringParser {

	private final static Log log = Logs.getLog(SpringParser.class);

	/**
	 * 解析包中的类
	 * 
	 * @param context
	 * @param packageNames
	 *            包名
	 * @return
	 */
	public ApiDoc parse(GenContext context, String... packageNames) {

		ArrayList<Class<?>> clzs = new ArrayList<Class<?>>();

		for (String pk : packageNames) {
			List<Class<?>> clz = Scans.me().scanPackage(pk);
			clzs.addAll(clz);
		}

		log.debug("mapway-doc parse classes: " + clzs.size());

		ApiDoc doc = new ApiDoc();
		doc.author = context.getAuthor();
		doc.basePath = context.getBasepath();
		doc.title = context.getDocTitle();

		for (Class<?> clz : clzs) {
			if (clz.getAnnotation(Controller.class) != null
					|| clz.getAnnotation(RestController.class) != null) {
				parseClass(doc, clz);
			}
		}
		return doc;
	}

	/**
	 * 解析某个类
	 * 
	 * @param doc
	 * @param clz
	 */
	private void parseClass(ApiDoc document, Class<?> clz) {

		Doc doc = clz.getAnnotation(Doc.class);

		if (doc == null) {
			log.debug("class " + clz.getName() + " is not annotated with Doc");
			return;
		}

		String path = doc.group();

		Group apigroup = document.findGroup(path);

		populateGroup(apigroup, clz);
	}

	/**
	 * 填充Group信息.
	 * 
	 * @param apigroup
	 * @param clz
	 */
	private void populateGroup(Group apigroup, Class<?> c) {
		String basepath = "";

		RequestMapping rm = c.getAnnotation(RequestMapping.class);
		if (rm != null) {
			String[] paths = rm.value();
			if (paths == null || paths.length == 0) {

			} else {
				basepath = paths[0];
			}
		}

		Method[] methods = c.getDeclaredMethods();
		List<Method> list = new ArrayList<Method>();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			RequestMapping rm1 = m.getAnnotation(RequestMapping.class);
			if (rm1 != null) {
				list.add(m);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			Method m = list.get(i);

			Entry entry = handleMethod(m);
			entry.parentClassName = c.getName();
			if (entry != null) {
				entry.relativePath = basepath + entry.relativePath;
				apigroup.entries.add(entry);
			}
		}
	}

	/**
	 * 解析方法，生成APIentry
	 * 
	 * @param m
	 * @return
	 */
	private Entry handleMethod(Method m) {
		Entry e = new Entry();

		RequestMapping rm = m.getAnnotation(RequestMapping.class);
		if (rm != null) {
			String[] paths = rm.value();
			if (paths == null || paths.length == 0) {

			} else {
				e.relativePath = paths[0];
			}

			e.invokeMethod = "GET";
			RequestMethod[] ms = rm.method();
			if (ms != null) {
				for (int i = 0; i < ms.length; i++) {
					RequestMethod rm0 = ms[i];
					if (rm0.equals(RequestMethod.POST)) {

						e.invokeMethod = "POST";
						break;
					}
				}
			}
		}

		if (e.relativePath.length() == 0) {
			return null;
		}

		e.methodName = m.getName();

		Doc summary = m.getAnnotation(Doc.class);
		if (summary != null) {
			e.title = summary.value();
			e.summary = summary.desc();
			e.order = summary.order();
			e.author = summary.author();
			e.state = summary.state();
		}

		Class<?>[] ps = m.getParameterTypes();
		Class<?> out = m.getReturnType();

		for (Class<?> clz : ps) {
			String name = clz.getSimpleName();
			if (name.startsWith("Http")) {
				continue;
			} else {
				ObjectInfo p = handleParameter(clz, name);
				if (p != null) {
					e.input.add(p);
				}
			}
		}
		e.output = handleParameter(out, "out");
		return e;
	}

	/**
	 * 处理参数
	 * 
	 * @param clz
	 * @return
	 */
	private ObjectInfo handleParameter(Class<?> clz, String name) {

		ObjectInfo p = new ObjectInfo();
		Doc summary = clz.getAnnotation(Doc.class);

		p.name = name == null ? clz.getSimpleName() : name;
		p.summary = summary == null ? "" : summary.value();

		if (isPrimitive(clz)) {
			p.type = clz.getSimpleName();
		} else {
			p.type = clz.getName();
		}
		String sum = "";

		// 循环处理父类中的解释
		Class<?> superclazz = clz.getSuperclass();
		while (superclazz != null) {
			Doc summary1 = superclazz.getAnnotation(Doc.class);
			if (sum.length() > 0) {
				sum += "<br/>";
			}
			sum += summary1 == null ? "" : summary1.desc();
			superclazz = superclazz.getSuperclass();
		}
		p.summary = sum + (summary == null ? "" : summary.desc());

		deeps = new Deeps();
		deeps.push(clz.getName(), deeps.getLevel());

		for (Field f : clz.getFields()) {

			ObjectInfo fld = handleField(f);
			if (fld != null) {
				p.fields.add(fld);
			}
		}
		return p;
	}

	/**
	 * 类深度信息
	 */
	Deeps deeps;

	/**
	 * 处理字段
	 * 
	 * @param f
	 * @return
	 */
	private ObjectInfo handleField(Field f) {
		deeps.incLevel();

		ApiField wf = f.getAnnotation(ApiField.class);
		if (wf != null) {

			ObjectInfo fi = new ObjectInfo();
			fi.manditary = wf.mandidate();
			fi.summary = wf.value();
			fi.length = wf.length();
			fi.example = wf.example();
			fi.name = f.getName();
			fi.type = f.getType().getName();

			// 记录类型的循环次数

			deeps.push(f.getType().getName(), deeps.getLevel());

			// 处理字段
			if (isPrimitive(f.getType())) {
				// 原始数据类型 无需解析子类
				fi.type = f.getType().getSimpleName();
			} else if (isList(f)) {

				Type type = getGenericType(f);
				Class<?> c = (Class<?>) type;
				fi.type = "List<" + c.getSimpleName() + ">";

				int count = deeps.getPreLevelCount(c.getName(),
						deeps.getLevel());
				if (count > 2) {
					// 不处理了，油循环引用
					deeps.decLevel();
					return null;

				}

				for (Field f1 : c.getFields()) {
					// 检查是否是循环引用
					ObjectInfo o = handleField(f1);
					if (o != null) {
						fi.fields.add(o);
					}
				}

			} else {
				// 该字段是一个对象类，循环处理此类
				int count = deeps.getPreLevelCount(f.getType().getName(),
						deeps.getLevel());
				if (count > 2) {
					// 不处理了，油循环引用
					deeps.decLevel();
					return null;
				}
				for (Field f1 : f.getType().getFields()) {
					ObjectInfo o = handleField(f1);
					if (o != null) {
						fi.fields.add(o);
					}
				}
			}

			deeps.decLevel();
			return fi;
		}
		deeps.decLevel();
		return null;
	}

	private boolean isList(Field f) {
		if (f.getType().isAssignableFrom(List.class)) {
			return true;
		}
		return false;
	}

	private Type getGenericType(Field f) {
		ParameterizedType pt = (ParameterizedType) f.getGenericType();
		return pt.getActualTypeArguments()[0];
	}

	private boolean isPrimitive(Class<?> c) {
		String name = c.getName();

		String[] ps = { "int", "Integer", "float", "FLoat", "Double", "double",
				"long", "Long", "Date", "DateTime", "String", "boolean",
				"Boolean", "char", "short", "byte", "Timestamp" };
		for (String s : ps) {
			if (name.contains(s)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {

		SpringParser p = new SpringParser();

		ApiDoc doc = p.parse(new GenContext(), "cn.mapway.doc2.test");
		System.out.println(Json.toJson(doc));
	}
}
