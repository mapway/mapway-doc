package cn.mapway.doc2.meta;

import java.util.Arrays;

/**
 * ApiDoc V2
 * 
 * @author zhangjianshe
 *
 */
public class ApiDoc {

	/**
	 * 作者
	 */
	public String author;

	/**
	 * 标题
	 */
	public String title;

	/**
	 * API版本
	 */
	public String version;

	/**
	 * API关联网址
	 */
	public String link;

	/**
	 * API简介
	 */
	public String summary;

	/**
	 * API 的整体说明文档
	 */
	public String description;

	/**
	 * 接口访问的基地址
	 */
	public String basePath;

	/**
	 * API分组的根节点
	 */
	public Group root;

	/**
	 * 
	 */
	public ApiDoc() {
		root = new Group();
	}

	/**
	 * 根据路径查找Group,如果不存在这个路径的对象，就在树中创建这个路径.
	 * 
	 * @param path
	 * @return
	 */
	public Group findGroup(String path) {

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
		Group g = root;

		for (int i = 0; i < paths.length; i++) {
			String p = paths[i];

			boolean find = false;
			for (Group sg : g.getChildGroups()) {
				if (sg.name.equals(p)) {// 找到节点
					g = sg;
					find = true;
				}
			}

			if (find == false) {
				// 没有找到节点 创建节点，并添加到节点树
				Group ng = new Group();
				ng.name = p;
				g.addChildGroup(ng);
				g = ng;
			}
		}

		return g;
	}
}
