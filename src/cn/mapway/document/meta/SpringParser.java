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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.mapway.document.annotation.Doc;
import cn.mapway.document.meta.module.ApiDocument;
import cn.mapway.document.meta.module.ApiEntry;
import cn.mapway.document.meta.module.ParameterInfo;

/**
 * 处理Spring注解 生成 ApiDocument对象
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class SpringParser extends DocAnotationBase {
	/**
	 * @param c
	 * @return
	 */
	public ApiDocument toApiDocument(Class<?> c) {
		ApiDocument api = new ApiDocument();

//		api.clsName = c.getSimpleName();
//		RequestMapping rm = c.getAnnotation(RequestMapping.class);
//		if (rm != null) {
//			String[] paths = rm.value();
//			if (paths == null || paths.length == 0) {
//
//			} else {
//				api.basePath = paths[0];
//			}
//		}
//
//		Doc summary = c.getAnnotation(Doc.class);
//		if (summary != null) {
//			api.title = summary.title();
//			api.author = summary.author();
//		}
//
//		Method[] methods = c.getDeclaredMethods();
//		List<Method> list = new ArrayList<Method>();
//		for (int i = 0; i < methods.length; i++) {
//			Method m = methods[i];
//			RequestMapping rm1 = m.getAnnotation(RequestMapping.class);
//			if (rm1 != null) {
//				list.add(m);
//			}
//		}
//
//		Collections.sort(list, new Comparator<Method>() {
//			@Override
//			public int compare(Method o1, Method o2) {
//				return o1.getName().compareTo(o2.getName());
//
//			}
//		});
//
//		for (int i = 0; i < list.size(); i++) {
//			Method m = list.get(i);
//			if (m.getName().equals("index")) {
//				continue;
//			}
//			ApiEntry entry = handleMethod(m);
//			if (entry != null) {
//				api.entries.add(entry);
//			}
//		}
		return api;
	}

	/**
	 * @param m
	 * @return
	 */
	private ApiEntry handleMethod(Method m) {

		ApiEntry e = new ApiEntry();

//		e.name = m.getName();
//		RequestMapping rm = m.getAnnotation(RequestMapping.class);
//		if (rm != null) {
//			String[] paths = rm.value();
//			if (paths == null || paths.length == 0) {
//
//			} else {
//				e.relativePath = paths[0];
//			}
//		}
//
//		if (e.relativePath.length() == 0) {
//			return null;
//		}
//
//		Doc summary = m.getAnnotation(Doc.class);
//		if (summary != null) {
//			e.title = summary.title();
//		}
//
//		RequestMethod[] mths = rm.method();
//
//		if (mths == null) {
//			e.invokeMethod = "GET";
//		} else {
//			for (RequestMethod mth : mths) {
//				e.invokeMethod += mth.name() + " ";
//			}
//			if (mths.length == 0) {
//				e.invokeMethod = "GET";
//			}
//		}
//
//		Class<?>[] ps = m.getParameterTypes();
//		Class<?> out = m.getReturnType();
//
//		for (Class<?> clz : ps) {
//			String name = clz.getSimpleName();
//			if (name.startsWith("Http")) {
//				continue;
//			} else {
//				ParameterInfo p = handleParameter(clz);
//				if (p != null) {
//					e.input.add(p);
//				}
//			}
//		}
//
//		e.output = handleParameter(out);
		return e;
	}

}
