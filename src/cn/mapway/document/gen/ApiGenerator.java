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

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import cn.mapway.document.meta.ILiveGen;
import cn.mapway.document.meta.module.WebData;

/**
 * API生成对象
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class ApiGenerator implements ILiveGen {
	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.mapway.document.meta.ILiveGen#genDocument(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public String genDocument(Class<?> clazz, String basepath) {
		if (clazz != null) {
			 if (clazz.getAnnotation(IocBean.class) != null) {
				NutzMvcGenerator g = new NutzMvcGenerator();
				return g.genDocument(clazz, basepath);
			} else {
				WebData d = new WebData();
				d.success = false;
				d.message = "不支持您所提供的类[only support SpringMvc,Nutz Mvc]";
				return Json.toJson(d);
			}
		} else {
			WebData d = new WebData();
			d.success = false;
			d.message = "你提供了空的类信息";
			return Json.toJson(d);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.mapway.document.meta.ILiveGen#genJavascript(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public String genJavascript(Class<?> clazz, String basepath) {
		if (clazz != null) {
			
			 if (clazz.getAnnotation(IocBean.class) != null) {
				NutzMvcGenerator g = new NutzMvcGenerator();
				return g.genJavascript(clazz, basepath);
			} else {
				WebData d = new WebData();
				d.success = false;
				d.message = "不支持您所提供的类[only support SpringMvc,Nutz Mvc]";
				return Json.toJson(d);
			}
		} else {
			WebData d = new WebData();
			d.success = false;
			d.message = "你提供了空的类信息";
			return Json.toJson(d);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.mapway.document.meta.ILiveGen#genTestPage(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public String genTestPage(Class<?> clazz, String basepath) {
		if (clazz != null) {
			if (clazz.getAnnotation(IocBean.class) != null) {
				NutzMvcGenerator g = new NutzMvcGenerator();
				return g.genTestPage(clazz, basepath);
			} else {
				WebData d = new WebData();
				d.success = false;
				d.message = "不支持您所提供的类[only support SpringMvc,Nutz Mvc]";
				return Json.toJson(d);
			}
		} else {
			WebData d = new WebData();
			d.success = false;
			d.message = "你提供了空的类信息";
			return Json.toJson(d);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.mapway.document.meta.ILiveGen#genGwtRpc(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public String genGwtRpc(Class<?> clazz, String basepath, String srcpath,
			String packagename) {
		if (clazz != null) {
			 if (clazz.getAnnotation(IocBean.class) != null) {
				NutzMvcGenerator g = new NutzMvcGenerator();
				return g.genGwtRpc(clazz, basepath, srcpath, packagename);
			} else {
				WebData d = new WebData();
				d.success = false;
				d.message = "不支持您所提供的类[only support SpringMvc,Nutz Mvc]";
				return Json.toJson(d);
			}
		} else {
			WebData d = new WebData();
			d.success = false;
			d.message = "你提供了空的类信息";
			return Json.toJson(d);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.mapway.document.meta.ILiveGen#genJavaConnector(java.lang.Class,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String genJavaConnector(Class<?> clazz, String basepath,
			String srcpath, String packagename) {
		if (clazz != null) {
			 if (clazz.getAnnotation(IocBean.class) != null) {
				NutzMvcGenerator g = new NutzMvcGenerator();
				return g.genJavaConnector(clazz, basepath, srcpath, packagename);
			} else {
				WebData d = new WebData();
				d.success = false;
				d.message = "不支持您所提供的类[only support SpringMvc,Nutz Mvc]";
				return Json.toJson(d);
			}
		} else {
			WebData d = new WebData();
			d.success = false;
			d.message = "你提供了空的类信息";
			return Json.toJson(d);
		}
	}

}
