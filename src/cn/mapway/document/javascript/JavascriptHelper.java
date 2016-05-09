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
package cn.mapway.document.javascript;

import java.io.IOException;

import cn.mapway.document.meta.module.ApiDocument;
import cn.mapway.document.meta.module.ApiEntry;
import cn.mapway.document.util.Template;

/**
 * @author zhangjianshe@navinfo.com
 * 
 */
public class JavascriptHelper {
	/**
	 * @param api
	 * @param basepath
	 */
	public final String toJavascript(ApiDocument api, String basepath) {
		String template;
		try {
			template = Template
					.readTemplate("/cn/mapway/document/util/resource/javascript.js");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		StringBuilder sb = new StringBuilder();
//		for (ApiEntry e : api.entries) {
//			sb.append("/*\r\n");
//			sb.append(" *" + e.name + "\r\n");
//			sb.append("*/\r\n");
//			sb.append(api.clsName + ".prototype." + e.name
//					+ "=function(data,ondata,onerror){\r\n");
//			if (e.invokeMethod.contains("GET")) {
//				sb.append("\t return this.http_get(this.basepath+'"
//						+ api.basePath + e.relativePath
//						+ "',data,ondata,onerror);\r\n");
//			} else if (e.invokeMethod.contains("POST")) {
//				sb.append("\t return this.http_post(this.basepath+'"
//						+ e.relativePath + "',data,ondata,onerror);\r\n");
//			}
//			sb.append("}\r\n");
//		}

		// System.out.println(sb.toString());

		template = template.replaceAll("\\$\\{title\\}", api.title);
		template = template.replaceAll("\\$\\{name\\}", api.clsName);
		template = template.replaceAll("\\$\\{methods\\}", sb.toString());
		template = template.replaceAll("\\$\\{basepath\\}", basepath);
		return template;
	}
}
