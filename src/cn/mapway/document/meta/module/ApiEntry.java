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
 * API入口
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class ApiEntry {
	/**
	 * 接口名称
	 */
	public String name;

	/**
	 * 接口说明
	 */
	public String summary = "";
	/**
	 * 接口相对路径
	 */
	public String relativePath = "";

	/**
	 * 调用类型
	 */
	public String invokeMethod = "";

	/**
	 * 输入参数
	 */
	public ArrayList<ParameterInfo> input = new ArrayList<ParameterInfo>();

	/**
	 * 输出参数
	 */
	public ParameterInfo output;
}
