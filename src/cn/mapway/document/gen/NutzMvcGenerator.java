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
package cn.mapway.document.gen;

import java.util.Properties;

import cn.mapway.document.doc.ParseType;
import cn.mapway.document.gen.module.GenContext;
import cn.mapway.document.meta.NutzParser;
import cn.mapway.document.meta.module.ApiDocument;

// TODO: Auto-generated Javadoc
/**
 * 对Nutz MVC controll类生成文档.
 *
 * @author zhangjianshe@navinfo.com
 */
class NutzMvcGenerator extends BaseGenerator {

	/* (non-Javadoc)
	 * @see cn.mapway.document.gen.BaseGenerator#toApiDocument(java.lang.Class, cn.mapway.document.gen.module.GenContext)
	 */
	public ApiDocument toApiDocument(Class<?> c, GenContext context) {
		NutzParser parser = new NutzParser();
		ApiDocument api = parser.toApiDocument(c);
		return api;
	}

	/**
	 * Parses the package.
	 *
	 * @param packageName the package name
	 * @param context the context
	 * @return the api document
	 */
	public ApiDocument parsePackage(String packageName, GenContext context) {
		NutzParser p = new NutzParser();
		ApiDocument api = p.parsePackage(packageName, context);
		return api;
	}

	/* (non-Javadoc)
	 * @see cn.mapway.document.meta.ILiveGen#parsePackage(cn.mapway.document.doc.ParseType, java.lang.String, cn.mapway.document.gen.module.GenContext)
	 */
	@Override
	public ApiDocument parsePackage(ParseType pt, String packageName,
			GenContext context) {
		return null;
	}

	

}
