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

import java.lang.reflect.Field;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Summary;
import cn.mapway.document.meta.module.FieldInfo;
import cn.mapway.document.meta.module.ParameterInfo;

/**
 * @author zhangjianshe@navinfo.com
 * 
 */
public class DocAnotationBase {
	/**
	 * 是否是基本类型
	 * 
	 * @param t
	 * @return
	 */
	public static final boolean isPrimitive(Class<?> t) {
		if (t.isPrimitive()) {
			return true;
		}
		if (t.getName().startsWith("java.lang")) {
			return true;
		}
		return false;
	}

	/**
	 * 处理参数
	 * 
	 * @param clz
	 * @return
	 */
	public static ParameterInfo handleParameter(Class<?> clz) {
		ParameterInfo p = new ParameterInfo();
		Summary summary = clz.getAnnotation(Summary.class);
		p.name = clz.getName();
		p.summary = summary == null ? "" : summary.value();
		p.clz = clz;
		for (Field f : clz.getFields()) {
			FieldInfo fld = handleField(f);
			if (fld != null) {
				p.flds.add(fld);
			}
		}
		return p;
	}

	/**
	 * 处理字段
	 * 
	 * @param f
	 * @return
	 */
	private static FieldInfo handleField(Field f) {
		ApiField wf = f.getAnnotation(ApiField.class);
		if (wf != null) {
			FieldInfo fi = new FieldInfo();
			fi.manditary = wf.mandidate();
			fi.fld = f;
			fi.summary = wf.value();
			return fi;
		}
		return null;
	}

}
