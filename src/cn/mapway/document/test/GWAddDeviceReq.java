package cn.mapway.document.test;

import java.util.ArrayList;
import java.util.List;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("网关调用云服务通知添加设备结果")
public class GWAddDeviceReq extends GWReq {

	@ApiField("添加设备列表")
	public List<AddDeviceInfo> deviceAddList;

	public GWAddDeviceReq() {
		deviceAddList = new ArrayList<AddDeviceInfo>();
	}
}
