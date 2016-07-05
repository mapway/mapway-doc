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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.nutz.json.Json;

// TODO: Auto-generated Javadoc
/**
 * Api文档结构.
 *
 * @author zhangjianshe@navinfo.com
 */
public class ApiDocument {

	/** 类名称. */
	public String clsName;
	
	/** API标题. */
	public String title = "API标题";

	/** 文档作者. */
	public String author = "zhangjianshe@mapway.cn";

	/** 控制器入口路径. */
	public String basePath = "";

	/** API根节点. */
	public ApiGroup root;

	/**
	 * Instantiates a new api document.
	 */
	public ApiDocument() {
		root = new ApiGroup();
		root.name = "/";
		root.summary = "分组根节点";
	}

	/**
	 * 按照字典规则排序 Group和Entry.
	 */
	public void sort()
	{
		root.sort();
	}
	
	/**
	 * 根据路径查找ApiGroup,如果不存在这个路径的对象，就在树中创建这个路径.
	 *
	 * @param path the path
	 * @return the api group
	 */
	public ApiGroup findGroup(String path) {

		if (path == null || path.length() == 0 || path.equals("/")) {
			return root;
		}

		String[] paths = path.split("/");
		if (paths.length > 0) {
			String p = paths[0];
			if (p.length() == 0) {
				paths = Arrays.copyOfRange(paths, 1, paths.length);
			}
		}
		ApiGroup g = root;

		for (int i = 0; i < paths.length; i++) {
			String p = paths[i];

			boolean find = false;
			for (ApiGroup sg : g.getChildGroups()) {
				if (sg.name.equals(p)) {// 找到节点
					g = sg;
					find = true;
				}
			}

			if (find == false) {
				// 没有找到节点 创建节点，并添加到节点树
				ApiGroup ng = new ApiGroup();
				ng.name = p;
				g.addChildGroup(ng);
				g = ng;
			}
		}

		return g;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		String path = "/ABC/DEF";
		ApiDocument d = new ApiDocument();
		ApiGroup g = d.findGroup(path);
		g = d.findGroup("ABC/White");
		System.out.println(Json.toJson(d.root));
	}

}
