/******************************************************************************
<pre>

           =============================================================
           -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -
           -    __] |__| |__| |\ | | __  | | |__| |\ | [__  |__| |___  -
           -   [___ |  | |  | | \| |__| _| | |  | | \| ___] |  | |___  -
           -           http://hi.baidu.com/zhangjianshe                -
           =============================================================

</pre>
 *******************************************************************************/
package cn.mapway.document.javaconnector;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.gen.GenClassInfo;
import cn.mapway.document.meta.DocAnotationBase;
import cn.mapway.document.meta.module.ApiDocument;
import cn.mapway.document.meta.module.ApiEntry;
import cn.mapway.document.meta.module.FieldInfo;
import cn.mapway.document.meta.module.ParameterInfo;
import cn.mapway.document.util.Template;

/**
 * 输出Java访问代码
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class JavaConnectorHelper extends DocAnotationBase {

	private ArrayList<GenClassInfo> objects = new ArrayList<GenClassInfo>();

	private void addGenClass(ParameterInfo t) {
		boolean find = false;
		for (int i = 0; i < objects.size(); i++) {
			GenClassInfo info = objects.get(i);
			String n1 = info.cls.clz.getName();
			String n2 = t.clz.getName();

			if (n1.equals(n2)) {
				find = true;
				break;
			}
		}
		if (find == false) {
			GenClassInfo info = new GenClassInfo();
			info.cls = t;
			info.gen = false;
			objects.add(info);
		}
	}

	/**
	 * @param api
	 * @param srcpath
	 * @param packagename
	 * @return
	 */
	public String toSource(ApiDocument api, String basepath, String srcpath,
			String packagename) {

		String template;
		try {
			template = Template
					.readTemplate("/cn/mapway/document/util/resource/connect.txt");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		StringBuilder imports = new StringBuilder();
		StringBuilder apis = new StringBuilder();
		StringBuilder modules = new StringBuilder();

		for (int i = 0; i < api.entries.size(); i++) {
			ApiEntry e = api.entries.get(i);
			codeEntry(imports, apis, e);
		}

		genModules(modules);

		template = template.replaceAll("\\$\\{package\\}", packagename);
		template = template.replaceAll("\\$\\{name\\}", api.clsName
				+ "Connector");
		template = template.replaceAll("\\$\\{basepath\\}", basepath);
		template = template.replaceAll("\\$\\{importModules\\}",
				imports.toString());
		template = template.replaceAll("\\$\\{apis\\}", apis.toString());
		template = template.replaceAll("\\$\\{modules\\}", modules.toString());
		return template;
	}

	/**
	 * 处理接口中模型
	 * 
	 * @param apis
	 */
	private void genModules(StringBuilder modules) {

		for (GenClassInfo ci : objects) {
			genModule(modules, ci);
		}
	}

	/**
	 * 生成模型
	 * 
	 * @param modules
	 * @param ci
	 */
	private void genModule(StringBuilder modules, GenClassInfo ci) {
		ParameterInfo pi = ci.cls;
		modules.append("\tpublic static class " + pi.clz.getSimpleName()
				+ "{\r\n");
		for (FieldInfo fi : pi.flds) {
			Field f = fi.fld;
			modules.append("\t/**\r\n");
			modules.append("\t * " + fi.summary + "\r\n");
			modules.append("\t */\r\n");
			ApiField wf = f.getAnnotation(ApiField.class);
			String isstatic = Modifier.isStatic(f.getModifiers()) ? "static"
					: "";

			if (wf != null) {
				if (Modifier.isFinal(f.getModifiers())) {

					String dv = "";
					try {
						dv = f.get(ci).toString();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					if (f.getType().getSimpleName().equals("String")) {
						dv = "\"" + dv + "\"";
					}
					modules.append("\tpublic final " + isstatic + " "
							+ f.getType().getSimpleName() + " " + f.getName()
							+ "=" + dv + ";\r\n");
					continue;
				}

				if (isPrimitive(f.getType())) {
					modules.append("\tpublic " + isstatic + " "
							+ f.getType().getSimpleName() + " " + f.getName()
							+ ";\r\n");
					continue;
				}
				if (f.getType().isAssignableFrom(List.class)) {
					// list
					Type fc = f.getGenericType();
					if (fc == null) {
						modules.append("\tpublic " + isstatic + " List<?> "
								+ f.getName() + ";\r\n");
						continue;
					}
					if (fc instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) fc;
						Class<?> t = (Class<?>) pt.getActualTypeArguments()[0];

						modules.append("\tpublic " + isstatic + " List<"
								+ t.getSimpleName() + "> " + f.getName()
								+ ";\r\n");
						continue;
					}
				}
				modules.append("\tpublic " + isstatic + " "
						+ f.getType().getSimpleName() + " " + f.getName()
						+ ";\r\n");
			}
		}
		modules.append("\t}\r\n");
	}

	/**
	 * 输出接口的代码
	 * 
	 * @param imports
	 * @param apis
	 * @param e
	 */
	private void codeEntry(StringBuilder imports, StringBuilder apis, ApiEntry e) {

		StringBuilder method = new StringBuilder();

		String outp = e.output.clz.getSimpleName();

		String inputp = "";
		String webp = "";
		if (e.input.size() > 0) {
			inputp = e.input.get(0).clz.getSimpleName() + " req";
			webp = "Json.toJson(req)";
		} else {
			webp = "\"\"";
		}

		method.append("\t/**\r\n");
		method.append("\t * " + e.title + "\r\n");
		method.append("\t *\r\n");
		method.append("\t * @param req 请求数据包\r\n");
		method.append("\t * @param 返回数据包\r\n");
		method.append("\t */\r\n");
		method.append("\tpublic " + outp + " " + e.name + "(" + inputp
				+ ") throws Exception{\r\n");
		method.append("\t\t" + outp + " r=null;\r\n");
		if (e.invokeMethod.contains("POST")) {
			method.append("\t\tr=webpost(\"" + e.relativePath + "\", " + webp
					+ ", " + outp + ".class);\r\n");
		} else {
			method.append("\t\tr=webget(\"" + e.relativePath + "\", " + webp
					+ ", " + outp + ".class);\r\n");
		}
		method.append("\t\treturn r;\r\n");
		method.append("\t}\r\n");

		apis.append(method.toString());

		System.out.print(e.output.name + " " + e.input.size() + "\r\n");
		handleModule(e.output);
		if (e.input.size() > 0) {
			handleModule(e.input.get(0));
		}
	}

	/**
	 * 处理参数
	 * 
	 * @param info
	 */
	private void handleModule(ParameterInfo info) {
		if (isPrimitive(info.clz)) {
			return;
		}
		addGenClass(info);
		for (FieldInfo fi : info.flds) {
			Field f = fi.fld;
			ApiField wf = f.getAnnotation(ApiField.class);
			if (wf != null) {

				if (isPrimitive(f.getType())) {

				} else if (f.getType().isAssignableFrom(List.class)) {
					// list
					Type fc = f.getGenericType();
					if (fc == null) {
						continue;
					}
					if (fc instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) fc;
						Class<?> t = (Class<?>) pt.getActualTypeArguments()[0];

						ParameterInfo pi = handleParameter(t);
						handleModule(pi);
					}

				} else {

					ParameterInfo pi = handleParameter(f.getType());
					if (isPrimitive(pi.clz)) {
					} else {
						addGenClass(pi);
					}
				}
			}
		}
	}
}
