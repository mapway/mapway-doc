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

import cn.mapway.document.doc.ApiDocumentHelper;
import cn.mapway.document.gen.module.GenContext;
import cn.mapway.document.gwtprc.GwtRpcHelper;
import cn.mapway.document.javaconnector.JavaConnectorHelper;
import cn.mapway.document.javascript.JavascriptHelper;
import cn.mapway.document.meta.ILiveGen;
import cn.mapway.document.meta.module.ApiDocument;
import cn.mapway.document.testpage.TestPageHelper;

/**
 * @author zhangjianshe@navinfo.com
 * 
 */
public abstract class BaseGenerator implements ILiveGen {
	/**
	 * 根据类信息生成API元数据信息
	 * 
	 * @param c
	 * @param basepath
	 * @return
	 */
	public abstract ApiDocument toApiDocument(Class<?> clazz, GenContext context);

	public String genDocument(Class<?> c, GenContext config) {
		ApiDocument api = toApiDocument(c, config);
		ApiDocumentHelper helper = new ApiDocumentHelper();
		String body = helper.gendoc(api,config);
		return body;
	}

	public String genJavascript(Class<?> c,  String basepath) {
		
		ApiDocument api = toApiDocument(c,  new GenContext());
		JavascriptHelper helper = new JavascriptHelper();
		String body = helper.toJavascript(api, basepath);
		return body;
	}

	public String genTestPage(Class<?> c, String basepath) {
		ApiDocument api = toApiDocument(c, new GenContext());
		TestPageHelper helper = new TestPageHelper();
		String jscode = genJavascript(c, basepath);
		String body = helper.toTestPage(api, basepath, jscode);
		return body;
	}

	public String genGwtRpc(Class<?> c, String basepath, String srcpath,
			String packagename) {
		GenContext context=new GenContext();
		ApiDocument api = toApiDocument(c, context);
		GwtRpcHelper helper = new GwtRpcHelper();
		String body = helper.toSource(api, basepath, srcpath, packagename);
		return body;
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
		GenContext context=new GenContext();
		ApiDocument api = toApiDocument(clazz,context );
		JavaConnectorHelper helper = new JavaConnectorHelper();
		String body = helper.toSource(api, basepath, srcpath, packagename);
		return body;
	}
}
