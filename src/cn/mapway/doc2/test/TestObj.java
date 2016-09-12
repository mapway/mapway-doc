package cn.mapway.doc2.test;

import java.util.List;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;
import cn.mapway.document.doc.ParseType;
import cn.mapway.document.gen.module.ApiEntry;

@Doc("sad")
public class TestObj {
	@ApiField("eq")
	public int a;
	@ApiField("eq")
	public Integer b;
	@ApiField("eq")
	public List<String> c;
	@ApiField("eq")
	public List<ParseType> d;
	@ApiField("eq")
	public ApiEntry e;

}
