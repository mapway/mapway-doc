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

import cn.mapway.document.meta.RsParser;
import cn.mapway.document.meta.module.ApiDocument;

/**
 * 对Nutz MVC controll类生成文档
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
class RsMvcGenerator  extends BaseGenerator {

	/**
	 * 根据类信息生成API元数据信息
	 * 
	 * @param c
	 * @param basepath
	 * @return
	 */
	public ApiDocument toApiDocument(Class<?> c, String basepath) {
		RsParser parser = new RsParser();
		ApiDocument api = parser.toApiDocument(c);
		return api;
	}

	

}
