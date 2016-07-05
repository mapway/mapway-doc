package cn.mapway.document.test;

import java.util.ArrayList;
import java.util.List;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

// TODO: Auto-generated Javadoc
/**
 * The Class GWAddDeviceReq.
 */
@Doc("网关调用云服务通知添加设备结果")
public class GWAddDeviceReq extends GWReq {

	/** The device add list. */
	@ApiField("添加设备列表")
	public List<AddDeviceInfo> deviceAddList;

	/**
	 * Instantiates a new GW add device req.
	 */
	public GWAddDeviceReq() {
		deviceAddList = new ArrayList<AddDeviceInfo>();
	}
}
