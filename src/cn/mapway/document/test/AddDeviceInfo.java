package cn.mapway.document.test;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

// TODO: Auto-generated Javadoc
/**
 * The Class AddDeviceInfo.
 */
@Doc("设备信息")
public class AddDeviceInfo {

	/** The device type. */
	@ApiField(value = "设备类型", length = 4)
	public String deviceType;

	/** The Installation address. */
	@ApiField(value = "安装位置", length = 200)
	public String InstallationAddress;

	/** The device mac address. */
	@ApiField(value = "设备MAC,现在采用设备mac地址，此值作为数据库中hedphysicalflag的值，hedid采用随机数计算。该值必须有", length = 4)
	public String deviceMacAddress;

	/** The device state. */
	@ApiField(value = "设备安装状态<br/>2未安装，1安装")
	public String deviceState;

	/** The manufactor. */
	@ApiField(value = "制造厂商名称", length = 128)
	public String manufactor;

	/** The device model. */
	@ApiField(value = "设备型号", length = 64)
	public String deviceModel;

	/** The devicevolume. */
	@ApiField(value = "装机容量", length = 255)
	public String devicevolume;

	/** The vendor id. */
	@ApiField(value = "出厂设备编号", length = 128)
	public String vendorId;

	/** The installation date. */
	@ApiField(value = "安装日期,字符串必须为YYYY-MM-dd HH:MM:SS", length = 4)
	public String installationDate;

	/** The dateof production. */
	@ApiField(value = "出厂日期,字符串必须为YYYY-MM-dd HH:MM:SS", length = 4)
	public String dateofProduction;

	/** The whether totalmeter. */
	@ApiField(value = "是否总量表 1是，0不是", length = 1)
	public String whetherTotalmeter;

	/** The volume unit. */
	@ApiField(value = "容量单位", length = 16)
	public String volumeUnit;
	
	/** The device name. */
	@ApiField(value = "设备名字", length = 64)
	public String deviceName;
	
	/** The device connect type. */
	@ApiField(value = "设备连接类型,例如01-wifi；02-blue；03-470；", length = 4)
	public String deviceConnectType;
}
