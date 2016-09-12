package cn.mapway.doc2.meta;

import java.util.ArrayList;
import java.util.List;

/*
 * 类对象信息
 */
public class ObjectInfo {
	public String name;
	public String type;
	public String summary;
	public String example;
	public boolean manditary;
	public int length = 0;
	public List<ObjectInfo> fields = new ArrayList<ObjectInfo>();
	
}
