package cn.mapway.doc2.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("return value")
public class Ret {
	@ApiField("double value")
	public Double d;
}
