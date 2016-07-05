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
package cn.mapway.document.testpage;

import java.io.IOException;

import org.nutz.json.Json;

import cn.mapway.document.meta.module.ApiDocument;
import cn.mapway.document.util.Template;

// TODO: Auto-generated Javadoc
/**
 * The Class TestPageHelper.
 *
 * @author zhangjianshe@navinfo.com
 */
public class TestPageHelper {
	
	/**
	 * To test page.
	 *
	 * @param api the api
	 * @param basepath the basepath
	 * @param jscode the jscode
	 * @return the string
	 */
	public String toTestPage(ApiDocument api, String basepath, String jscode) {

		String jquery;
		String template;
		try {
			template = Template
					.readTemplate("/cn/mapway/document/util/resource/apitest.html");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		template = template.replaceAll("\\$\\{Controller\\}", api.clsName);

		try {
			jquery = Template
					.readTemplate("/cn/mapway/document/util/resource/jquery.txt");
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		template = template.replaceAll("\\$\\{basepath\\}", basepath);

		template = template.replaceAll("\\$\\{title\\}", api.title);

		template = template.replaceAll("\\$\\{apiinfo\\}", "var apiinfo="
				+ Json.toJson(api));

		jscode = java.util.regex.Matcher.quoteReplacement(jscode);
		template = template.replaceAll("\\$\\{JSCALLER\\}", jscode);
		jquery = java.util.regex.Matcher.quoteReplacement(jquery);

		template = template.replaceAll("\\$\\{JQUERY\\}", jquery);
		return template;

	}
}
