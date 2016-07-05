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

import java.lang.reflect.Field;

// TODO: Auto-generated Javadoc
/**
 * 字段信息.
 *
 * @author zhangjianshe@navinfo.com
 */
public class FieldInfo {

	/** 字段信息. */
	public Field fld;
	
	/** 字段说明. */
	public String summary = "";

	/** 是否强制填写. */
	public boolean manditary = true;

	/** 长度. */
	public int length;

	/** 例子. */
	public String example;

}
