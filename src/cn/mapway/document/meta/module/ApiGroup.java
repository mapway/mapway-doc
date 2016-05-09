package cn.mapway.document.meta.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * API接口的树形组织
 * 
 * @author zhangjianshe
 *
 */
public class ApiGroup {

	/**
	 * 节点名称
	 */
	public String name;

	/**
	 * 节点说明
	 */
	public String summary;

	/**
	 * 子节点
	 */
	public List<ApiGroup> subGroup;

	/**
	 * 接口列表
	 */
	public List<ApiEntry> entries;

	public ApiGroup() {
		subGroup = new ArrayList<ApiGroup>();
		entries = new ArrayList<ApiEntry>();
	}
	
	public void sort()
	{
		
		Collections.sort(subGroup,new Comparator<ApiGroup>() {
			@Override
			public int compare(ApiGroup o1, ApiGroup o2) {
				return o1.name.compareTo(o2.name);
			}
		});

		Collections.sort(entries,new Comparator<ApiEntry>() {
			@Override
			public int compare(ApiEntry o1, ApiEntry o2) {
				return o1.relativePath.compareTo(o2.relativePath);
			}
		});
		
		for(ApiGroup g:subGroup)
		{
			g.sort();
		}
	}
}
