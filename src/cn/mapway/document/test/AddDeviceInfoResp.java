package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("添加设备返回数据包")
public class AddDeviceInfoResp {

	@ApiField("返回内容 01-设备类型不存在 02-其他")
	public String backContent;
}
