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

import cn.mapway.document.meta.NutzParser;
import cn.mapway.document.meta.module.ApiDocument;

/**
 * 对Nutz MVC controll类生成文档
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
class NutzMvcGenerator extends BaseGenerator {

	
	public ApiDocument toApiDocument(Class<?> c, String basepath) {
		NutzParser parser = new NutzParser();
		ApiDocument api = parser.toApiDocument(c);
		return api;
	}

	
}
