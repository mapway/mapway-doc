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
package cn.mapway.document.meta;

import cn.mapway.document.doc.ParseType;
import cn.mapway.document.gen.module.GenContext;
import cn.mapway.document.meta.module.ApiDocument;

/**
 * 在线文档生成接口
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public interface ILiveGen {

	public ApiDocument parsePackage(ParseType pt,String packageName, GenContext context);

	/**
	 * 生成文档页
	 * 
	 * @param c
	 *            将生成文档的对象信息
	 * @param basepath
	 *            接口访问的基地址
	 * @return
	 */
	public String genDocument(Class<?> clazz, GenContext context);

	/**
	 * 生成Javascript访问
	 * 
	 * @param c
	 *            将生成文档的对象信息
	 * @param basepath
	 *            接口访问的基地址
	 * @return
	 */
	public String genJavascript(Class<?> clazz, String basepath);

	/**
	 * 生成测试页面
	 * 
	 * @param c
	 *            将生成文档的对象信息
	 * @param basepath
	 *            接口访问的基地址
	 * @return
	 */
	public String genTestPage(Class<?> clazz, String basepath);

	/**
	 * 生成访问RPC
	 * 
	 * @param c
	 *            将生成文档的对象信息
	 * @param srcpath
	 *            路径
	 * @param packagename
	 *            生成代码包路径
	 * @return
	 */
	public String genGwtRpc(Class<?> c, String basepath, String srcpath,
			String packagename);

	/**
	 * 生成Java访问代码
	 * 
	 * @param clazz
	 * @param srcpath
	 * @param packagename
	 * @return
	 */
	public String genJavaConnector(Class<?> clazz, String basepath,
			String srcpath, String packagename);
}