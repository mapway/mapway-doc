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
package cn.mapway.document.gen.module;

import java.util.ArrayList;

/**
 * 参数信息
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class ParameterInfo {
	/**
	 * 参数名称
	 */
	public String name;

	/**
	 * 参数类型
	 */
	public String type;

	/**
	 * 参数说明
	 */
	public String summary;

	/**
	 * 参数的字段
	 */
	public ArrayList<FieldInfo> flds = new ArrayList<FieldInfo>();

	/**
	 * 引用的参数类型
	 */
	public Class<?> clz;
}
