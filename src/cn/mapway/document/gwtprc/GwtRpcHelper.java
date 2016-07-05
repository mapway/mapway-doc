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
package cn.mapway.document.gwtprc;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.gen.GenClassInfo;
import cn.mapway.document.meta.DocAnotationBase;
import cn.mapway.document.meta.module.ApiDocument;
import cn.mapway.document.meta.module.ApiEntry;
import cn.mapway.document.meta.module.FieldInfo;
import cn.mapway.document.meta.module.ParameterInfo;
import cn.mapway.document.util.Template;

// TODO: Auto-generated Javadoc
/**
 * 将 ApiDocument 转化为 Gwtrpc访问代码.
 *
 * @author zhangjianshe@navinfo.com
 */
public class GwtRpcHelper extends DocAnotationBase {

	/** The objects. */
	private ArrayList<GenClassInfo> objects = new ArrayList<GenClassInfo>();

	/**
	 * Adds the gen class.
	 *
	 * @param t the t
	 */
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
	 * To source.
	 *
	 * @param api the api
	 * @param basepath the basepath
	 * @param srcpath the srcpath
	 * @param packagename the packagename
	 * @return the string
	 */
	public String toSource(ApiDocument api, String basepath, String srcpath,
			String packagename) {

		String template;
		try {
			template = Template
					.readTemplate("/cn/mapway/document/util/resource/gwtconnect.txt");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		StringBuilder imports = new StringBuilder();
		StringBuilder apis = new StringBuilder();
		StringBuilder modules = new StringBuilder();

//		for (int i = 0; i < api.entries.size(); i++) {
//			ApiEntry e = api.entries.get(i);
//			codeEntry(imports, apis, e);
//		}

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
	 * 处理接口中模型.
	 *
	 * @param modules the modules
	 */
	private void genModules(StringBuilder modules) {

		for (GenClassInfo ci : objects) {
			genModule(modules, ci);
		}
	}

	/**
	 * 生成模型.
	 *
	 * @param modules the modules
	 * @param ci the ci
	 */
	private void genModule(StringBuilder modules, GenClassInfo ci) {
		ParameterInfo pi = ci.cls;
		String clzname = pi.clz.getSimpleName();
		modules.append("\tpublic static class " + clzname
				+ " extends JavaScriptObject{\r\n");
		modules.append("protected " + clzname + "(){}");
		modules.append("public native final boolean isDefine(String name)/*-{return !( this[name]===undefined || this[name]==null );}-*/;\r\n");
		modules.append("\tpublic final  native static " + clzname
				+ " create()/*-{return new Object();}-*/;\r\n");
		modules.append("\tpublic final  native static " + clzname
				+ " create(JavaScriptObject obj)/*-{return obj}-*/;\r\n");

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
					String type = getScriptType(f.getType());
					modules.append("\tpublic final  native" + isstatic + " "
							+ type + " get" + f.getName()
							+ "()/*-{return this." + f.getName() + ";}-*/;"
							+ ";\r\n");
					modules.append("\tpublic final  native" + isstatic
							+ " void " + " set" + f.getName() + "(" + type
							+ " v)/*-{this." + f.getName() + "=v;}-*/;"
							+ ";\r\n");
					continue;
				}
				if (f.getType().isAssignableFrom(List.class)) {
					// list
					Type fc = f.getGenericType();
					if (fc == null) {
						modules.append("\tpublic final  native " + isstatic
								+ " JsArray<?> " + " get" + f.getName()
								+ "()/*-{return this." + f.getName() + ";}-*/;"
								+ ";\r\n");
						modules.append("\tpublic final  native" + isstatic
								+ " void " + " set" + f.getName()
								+ "(JsArray<?> v)/*-{this." + f.getName()
								+ "=v;}-*/;" + ";\r\n");

						continue;
					}
					if (fc instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) fc;
						Class<?> t = (Class<?>) pt.getActualTypeArguments()[0];
						String type = getScriptListType(t);
						modules.append("\tpublic final native " + isstatic
								+ type + " get" + f.getName()
								+ "()/*-{return this." + f.getName() + ";}-*/;"
								+ ";\r\n");
						modules.append("\tpublic final  native" + isstatic
								+ " void " + " set" + f.getName() + "(" + type
								+ " v)/*-{this." + f.getName() + "=v;}-*/;"
								+ ";\r\n");
						continue;
					}
				}
				modules.append("\tpublic final  native" + isstatic + " "
						+ f.getType().getSimpleName() + " get" + f.getName()
						+ "()/*-{return this." + f.getName() + ";}-*/;"
						+ ";\r\n");
				modules.append("\tpublic final  native" + isstatic + " void "
						+ " set" + f.getName() + "("
						+ f.getType().getSimpleName() + " v)/*-{this."
						+ f.getName() + "=v;}-*/;" + ";\r\n");
			}
		}
		modules.append("\t}\r\n");
	}

	/**
	 * Gets the script type.
	 *
	 * @param t the t
	 * @return the script type
	 */
	private String getScriptType(Class<?> t) {
		String type = t.getSimpleName();
		if (type.equals("String") || type.equals("String")) {
			return "String";
		} else if (type.equals("Integer") || type.equals("int")) {
			return "int";
		} else if (type.equals("Double") || type.equals("double")) {
			return "double";
		} else if (type.equals("Long") || type.equals("long")) {
			return "double";
		} else if (type.equals("Float") || type.equals("float")) {
			return "double";
		} else if (type.equals("Boolean") || type.equals("boolean")) {
			return "boolean";
		} else
			return type;
	}

	/**
	 * Gets the script list type.
	 *
	 * @param t the t
	 * @return the script list type
	 */
	private String getScriptListType(Class<?> t) {
		String type = t.getSimpleName();
		if (type.equals("String") || type.equals("String")) {
			return "JsArrayString";
		} else if (type.equals("Integer") || type.equals("int")) {
			return "JsArrayInteger";
		} else if (type.equals("Double") || type.equals("double")) {
			return "JsArrayNumber";
		} else if (type.equals("Long") || type.equals("long")) {
			return "JsArrayNumber";
		} else if (type.equals("Float") || type.equals("float")) {
			return "JsArrayInteger";
		} else if (type.equals("Boolean") || type.equals("boolean")) {
			return "JsArrayBoolean";
		} else {

			return "JsArray<" + type + ">";
		}
	}

	/**
	 * 输出接口的代码.
	 *
	 * @param imports the imports
	 * @param apis the apis
	 * @param e the e
	 */
	private void codeEntry(StringBuilder imports, StringBuilder apis, ApiEntry e) {

		StringBuilder method = new StringBuilder();

		String outp = e.output.clz.getSimpleName();
		if (isPrimitive(e.output.clz)) {
			outp = "String";
		}
		String inputp = "";
		String webp = "";

		if (e.input.size() > 0) {
			inputp = e.input.get(0).clz.getSimpleName() + " req,";
			webp = "Jsons.toJson(req,\"\",\"\")";
		} else {
			webp = "\"\"";
		}

		method.append("\t/**\r\n");
		method.append("\t * " + e.name + "\r\n");
		method.append("\t *\r\n");
		method.append("\t * @param req 请求数据包\r\n");
		method.append("\t * @param 返回数据包\r\n");
		method.append("\t */\r\n");
		method.append("\tpublic  static void " + e.name + "(" + inputp
				+ " final IServerCallback<" + outp + "> handler){\r\n");

		method.append("\t\t String data=" + webp + ";\r\n");
		String m = outp.equals("String") ? "String" : "";

		if (e.invokeMethod.contains("POST")) {

			method.append("\t\tdoPost" + m + "(\"" + e.relativePath
					+ "\", data,handler);\r\n");
		} else {
			method.append("\t\tdoGet" + m + "(\"" + e.relativePath
					+ "\",data, handler);\r\n");
		}
		method.append("\t\treturn ;\r\n");
		method.append("\t}\r\n");

		apis.append(method.toString());

		System.out.print(e.output.name + " " + e.input.size() + "\r\n");
		handleModule(e.output);
		if (e.input.size() > 0) {
			handleModule(e.input.get(0));
		}
	}

	/**
	 * 处理参数.
	 *
	 * @param info the info
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
					handleModule(pi);
				}
			}
		}
	}
}
