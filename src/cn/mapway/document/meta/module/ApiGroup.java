package cn.mapway.document.meta.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.nutz.json.Json;

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
	private List<ApiGroup> subGroup;

	/**
	 * 父节点
	 */
	private ApiGroup   parent;
	
	/**
	 * 添加子節點
	 * @param g
	 */
	public void addChildGroup(ApiGroup g)
	{
		g.setParent(this);
		subGroup.add(g);
	}
	/**
	 * 清空子節點
	 */
	public void clearChildGroup()
	{
		subGroup.clear();
	}
	/**
	 * 移除子节点
	 * @param g
	 */
	public void removeChildGroup(ApiGroup g)
	{
		subGroup.remove(g);
	}
	
	
	public List<ApiGroup> getChildGroups()
	{
		return subGroup;
	}
	
	/**
	 * 获取节点的全路径
	 * @return
	 */
	public String getPath()
	{
		ArrayList<String> strArray=new ArrayList<String>();
		
		ApiGroup g=getParent();
		
		while(g!=null)
		{
			strArray.add(g.name);
			g=g.getParent();
		}
		
		StringBuilder sb=new StringBuilder();
		for(int i=strArray.size()-1;i>=0;i--)
		{
			if(strArray.get(i).equals("/"))
			{
				continue;
			}
			sb.append("/"+strArray.get(i));
		}
		
		return sb.toString();
	}
	/**
	 * 接口列表
	 */
	public List<ApiEntry> entries;

	public ApiGroup() {
		subGroup = new ArrayList<ApiGroup>();
		entries = new ArrayList<ApiEntry>();
		setParent(null);
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

	public ApiGroup getParent() {
		return parent;
	}

	public void setParent(ApiGroup parent) {
		this.parent = parent;
	}
}
