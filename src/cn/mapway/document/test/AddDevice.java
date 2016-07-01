package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("添加的设备信息")
public class AddDevice {

	@ApiField("网关ID")
	public String gatewayId;
	@ApiField("设备类型ID")
	public String deviceTypeId;
	@ApiField("设备连接类型")
	public String sLinkType;
	@ApiField("设备mac地址")
	public String deviceMacAddress;

}
