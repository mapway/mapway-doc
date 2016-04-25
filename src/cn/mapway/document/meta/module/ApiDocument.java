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
package cn.mapway.document.meta.module;

import java.util.ArrayList;

/**
 * Api文档结构
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class ApiDocument {

	/**
	 * 类名称
	 */
	public String clsName;
	/**
	 * API标题
	 */
	public String title = "API标题";

	/**
	 * 文档作者
	 */
	public String author = "zhangjianshe@mapway.cn";

	/**
	 * 控制器入口路径
	 */
	public String basePath = "";

	/**
	 * 入口方法
	 */
	public ArrayList<ApiEntry> entries = new ArrayList<ApiEntry>();
}
