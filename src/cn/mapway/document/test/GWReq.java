package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("网关请求基本信息")
public class GWReq {
	@ApiField(value = "网关ID,长度20,网关注册格式：包头+网关MAC地址+校验和+包尾<br/>"
			+ "例如:EN_78A3510982DAD1_NE" + "<ol><li>包头:EN_"
			+ "<li>网关MAC地址:78A3510982DA(16进制表示)"
			+ "<li>校验和:78+A3+51+09+82+DA=D1(16进制计算的和，结果取最后2位)"
			+ "<li>包尾:_NE</ol>", length = 20)
	public String gatewayId = "";

	@ApiField(value = "网关ID,重新注册会重新计算token", length = 36)
	public String tokenId = "";
}
