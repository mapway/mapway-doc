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

import cn.mapway.document.doc.ParseType;
import cn.mapway.document.gen.module.GenContext;
import cn.mapway.document.meta.NutzParser;
import cn.mapway.document.meta.SpringParser;
import cn.mapway.document.meta.module.ApiDocument;

// TODO: Auto-generated Javadoc
/**
 * 对SpringMVC controll类生成文档.
 *
 * @author zhangjianshe@navinfo.com
 */
class SpringMvcGenerator extends BaseGenerator {

	/**
	 * 根据类信息生成API元数据信息.
	 *
	 * @param c the c
	 * @param context the context
	 * @return the api document
	 */
	public ApiDocument toApiDocument(Class<?> c, GenContext context) {
		SpringParser springHelper = new SpringParser();
		ApiDocument api = springHelper.toApiDocument(c);
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
		
		SpringParser p = new SpringParser();
		ApiDocument api = p.parsePackage(packageName, context);
		return api;
	}
	
	/* (non-Javadoc)
	 * @see cn.mapway.document.meta.ILiveGen#parsePackage(cn.mapway.document.doc.ParseType, java.lang.String, cn.mapway.document.gen.module.GenContext)
	 */
	@Override
	public ApiDocument parsePackage(ParseType pt, String packageName,
			GenContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
