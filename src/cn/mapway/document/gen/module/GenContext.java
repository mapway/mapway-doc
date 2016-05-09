package cn.mapway.document.gen.module;

import java.util.Properties;

/**
 * 配置信息
 * 
 * @author zhangjianshe
 *
 */
public class GenContext extends Properties {

	private final static String DOMAIN = "DOMAIN";
	private final static String BASEPATH = "BASEPATH";
	private final static String AUTHOR = "AUTHOR";
	private final static String DOCTITLE = "DOCTITLE";

	public String getDomain() {
		return getProperty(DOMAIN);
	}

	public void setDomain(String domain) {
		put(DOMAIN, domain);
	}

	public String getBasepath() {
		return getProperty(BASEPATH);
	}

	public void setBasepath(String basepath) {
		put(BASEPATH, BASEPATH);
	}

	public void setDocTitle(String docTitle) {
		put(DOCTITLE, docTitle);
	}

	public String getDocTitle() {
		return getProperty(DOCTITLE);
	}

	public void setAuthor(String author) {
		put(AUTHOR, author);
	}

	public String getAuthor() {
		return getProperty(AUTHOR);
	}
}
