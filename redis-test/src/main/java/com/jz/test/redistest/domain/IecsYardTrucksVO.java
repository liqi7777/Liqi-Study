package com.jz.test.redistest.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 集卡资料 VO
 * @version 1.0
 * @Description
 * @Author wangj
 * @email 971981280@qq.com
 * @date 2021-01-12 12:31:03
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IecsYardTrucksVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 內集卡心跳状态
	 */
	private String trkHeartStatus;

	/**
	* 集卡号
	*/
	private String trkTrkno;

	/**
	* 集卡类型(ITK内集卡 OTK外集卡 AIV)
	*/
	private String trkType;

	/**
	* $column.comments
	*/
	private String trkTrktag;

	/**
	* 集卡状态
	*/
	private String trkMchstatus;

	/**
	* $column.comments
	*/
	private String trkRegRegioncd;

	/**
	* $column.comments
	*/
	private String trkTrnTrnscomcd;

	/**
	* 所属码头代码
	*/
	private String trkTerTermcd;

	/**
	* 长
	*/
	private Long trkLength;

	/**
	* 宽
	*/
	private Long trkWidth;

	/**
	* 净重
	*/
	private Long trkNetwg;

	/**
	* 载重
	*/
	private Long trkLoad;

	/**
	* 型号
	*/
	private String trkModel;

	/**
	* 制造商
	*/
	private String trkManufac;

	/**
	* $column.comments
	*/
	private String trkOwner;

	/**
	* $column.comments
	*/
	private String trkFefg;

	/**
	* $column.comments
	*/
	private String trkGcnGcraneno;

	/**
	* $column.comments
	*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime trkCuropdate;

	/**
	* $column.comments
	*/
	private String trkOpstep;

	/**
	* $column.comments
	*/
	private String trkMdtno;

	/**
	* $column.comments
	*/
	private String trkGroupno;

	/**
	* $column.comments
	*/
	private String trkCurshiftno;

	/**
	* $column.comments
	*/
	private String trkOnoff;

	/**
	* 当前状态
	*/
	private String trkCurstatus;

	/**
	* $column.comments
	*/
	private String trkTrailertype;

	/**
	* $column.comments
	*/
	private String trkCurdriver;

	/**
	* $column.comments
	*/
	private String trkFlat;

	/**
	* $column.comments
	*/
	private String trkLoginip;

	/**
	* $column.comments
	*/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime trkSendtime;

	/**
	* $column.comments
	*/
	private String trkTpstype;

	/**
	* 能源类型(0电动;1燃油)
	*/
	private Long trkEnergytype;

	/**
	 * 所在区域
	 */
	private String trkArea;
	private String trkServeradd;
	private String trkUdpServeradd;
	private String trkVinNo;

	private String trkNo;

	//默认车道号
	private String trkLaneNo;
	//车辆类型中文显示
	private String trkTypeN;
	//车辆状态中文显示
	private String trkMchstatusN;
	/**
	 * 第一段通信(0正常，1失败)
	 */
	private String trkCommstatus;

	private String trkCommstatus2;
	/**
	 * 集卡是否能定位（Y/N） 空代表N
	 */
	private String trkLocation;

	/**
	 * 出勤状态 空代表N
	 */
	private String trkAttendanceStatus;


	/**
	 * 作业路id/RouteId
	 */
	private Long iswrRouteid;
	/**
	 * AIV版本  2/2代  3/3代
	 */
	private String trkVersion;

	/**
	 * TOS设备状态 0正常，1故障
	 */
	private String trkTosStatus;

	/**
	 * 自动化作业状态
	 */
	private String automaticStatus;

	/**
	 *车辆调度类型(1-IECS调度车辆,0-TOPS调度车辆,2-共享调度车辆)
	 */
	private String trkUseType;

	/**
	 * 是否装司南(1:已安装，0：未安装)
	 */
	private String trkSinanStatus;

	/** *MAC地址 */
	private String trkMacAddress;

	/** * IP地址 */
	private String trkIpAddress;

	/**
	 *子网掩码
	 */
	private String trkSubMask;

	/**
	 * 端口号
	 */
	private Integer trkPort;

	private String equipmentNo;

	private String trkLaneno;


	public String getTrkLaneNo() {
		return trkLaneNo;
	}

	public void setTrkLaneNo(String trkLaneNo) {
		this.trkLaneNo = trkLaneNo;
	}

	public String getTrkLaneno() {
		return trkLaneno;
	}

	public void setTrkLaneno(String trkLaneno) {
		this.trkLaneno = trkLaneno;
	}
	/**
	 * 重磅标志  Y表示具备载重50吨以上，N表示不具备
	 */
	private String isiHeavyflag;

	/**
	 * 拖运危险品资质 Y表示具备，为空或N表示不具备
	 */
	private String isiAptitude;

	/**
	 * 通信类型(1-mqtt 0-http)
	 */
	private String methods;

	/**
	 * stop：紧停，start:正常行驶，inHand:处理中）
	 */
	private String trkStopstartStatus;

	private String tosPlateSwitch;
	/**
	 * 是否发送桥吊和口门信息给AIV 1-是 0-否 默认是
	 */
	private String  trkSendInfo;
	/**
	 *  车辆使用的uwb导航版本 3-第三版 4-第四版     默认稳定版3
	 */
	private String  uwbVersion;

	/**
	 * 人工控制状态: 1-开始人工控制  2-解除人工控制 (AIV才有值)
	 */
	private String trkLabourControls;

	/**
	 * 警戒距离
	 */
	@TableField(value = "TRK_ALERT_DISTANCE")
	private Double trkAlertDistance;

	/**
	 * 立即停车距离
	 */
	@TableField(value = "TRK_IMMEDIATE_STOP_DISTANCE")
	private Double trkImmediateStopDistance;

	/**
	 * AIV恢复延时
	 */
	@TableField(value = "TRK_RECOVERY_DELAY")
	private Long trkRecoveryDelay;
}
