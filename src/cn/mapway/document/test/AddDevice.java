package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

// TODO: Auto-generated Javadoc
/**
 * The Class AddDevice.
 */
@Doc("添加的设备信息")
public class AddDevice {

	/** The gateway id. */
	@ApiField("网关ID")
	public String gatewayId;
	
	/** The device type id. */
	@ApiField("设备类型ID")
	public String deviceTypeId;
	
	/** The s link type. */
	@ApiField("设备连接类型")
	public String sLinkType;
	
	/** The device mac address. */
	@ApiField("设备mac地址")
	public String deviceMacAddress;

}
