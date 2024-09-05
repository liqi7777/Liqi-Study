package com.example.fastjson;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Sky
 * create 2020/05/19
 * email sky.li@ixiaoshuidi.com
 **/
@Data
@NoArgsConstructor
public class StateInRentFeeBO_v2 {
    /**
     * 房间id
     */
    private Integer roomId;

    /**
     * 合同id
     */
    private Integer contractId;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 公寓id
     */
    private String apartmentId;

    /**
     * 房间名称
     */
    private String roomName;

    /**
     * 公寓名称
     */
    private String apartmentName;

    /**
     * 月份信息(yyyy年MM月)
     */
    private String monthInfo;

    /**
     * 租金收入（本期收入）
     */
    private BigDecimal rentIn;

    /**
     * 租金折让（本期折让）
     */
    private BigDecimal rentRebate;

    /**
     * 租金累计收入
     */
    private BigDecimal accRentIn;

    /**
     * 租金累计折让
     */
    private BigDecimal accRentRebate;

    /**
     * 其他本期收入
     */
    private BigDecimal feesIn;

    /**
     * 其他本期折让
     */
    private BigDecimal feesRebate;

    /**
     * 其他累计收入
     */
    private BigDecimal accFeesIn;

    /**
     * 其他累计折让
     */
    private BigDecimal accFeesRebate;

    /**
     * 费项id
     */
    private Integer feeTypeId;

    /**
     * 费项名称
     */
    private String feeTypeName;

    /**
     * 费用承担人
     */
    private String feePerson;


    /**
     * 房源类型（集分兼容下 1：集中，0:分散）
     */
    private String isConcentrated;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String district;

    /**
     * 门店ID
     */
    private String storeorgstructureId;

    /**
     * 租客姓名
     */
    private String customerName;

    /**
     * 当月有多少天
     */
    private String monthDays;

    /**
     * 合同开始时间
     */
    private String contractBegin;

    /**
     * 合同结束时间
     */
    private String contractEnd;


    public StateInRentFeeBO_v2(Integer roomId, Integer contractId, String contractNo, String apartmentId, String roomName, String apartmentName, String monthInfo, BigDecimal rentIn, BigDecimal rentRebate, BigDecimal accRentIn, BigDecimal accRentRebate, BigDecimal feesIn, BigDecimal feesRebate, BigDecimal accFeesIn, BigDecimal accFeesRebate, Integer feeTypeId, String feeTypeName) {
        this.roomId = roomId;
        this.contractId = contractId;
        this.contractNo = contractNo;
        this.apartmentId = apartmentId;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.monthInfo = monthInfo;
        this.rentIn = rentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.rentRebate = rentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentIn = accRentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentRebate = accRentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feeTypeId = feeTypeId;
        this.feeTypeName = feeTypeName;
        this.feesIn = feesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feesRebate = feesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesIn = accFeesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesRebate = accFeesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * v2现用
     **/
    public StateInRentFeeBO_v2(Integer roomId, Integer contractId, String contractNo, String apartmentId, String roomName, String apartmentName, String monthInfo, BigDecimal rentIn, BigDecimal rentRebate, BigDecimal accRentIn, BigDecimal accRentRebate, BigDecimal feesIn, BigDecimal feesRebate, BigDecimal accFeesIn, BigDecimal accFeesRebate, Integer feeTypeId, String feeTypeName, String isConcentrated, String city, String district, String storeorgstructureId, String customerName, String monthDays, String contractBegin, String contractEnd) {
        this.roomId = roomId;
        this.contractId = contractId;
        this.contractNo = contractNo;
        this.apartmentId = apartmentId;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.monthInfo = monthInfo;
        this.rentIn = rentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.rentRebate = rentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentIn = accRentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentRebate = accRentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feeTypeId = feeTypeId;
        this.feeTypeName = feeTypeName;
        this.feesIn = feesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feesRebate = feesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesIn = accFeesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesRebate = accFeesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.isConcentrated = isConcentrated;
        this.city = city;
        this.district = district;
        this.storeorgstructureId = storeorgstructureId;
        this.customerName = customerName;
        this.monthDays = monthDays;
        this.contractBegin = contractBegin;
        this.contractEnd = contractEnd;
    }




    /**
     * v2 现用
     **/
    public StateInRentFeeBO_v2(Integer roomId, Integer contractId, String contractNo, String apartmentId, String roomName, String apartmentName, String monthInfo, BigDecimal rentIn, BigDecimal rentRebate, BigDecimal accRentIn, BigDecimal accRentRebate, BigDecimal feesIn, BigDecimal feesRebate, BigDecimal accFeesIn, BigDecimal accFeesRebate, String isConcentrated, String city, String district, String storeorgstructureId, String customerName, String monthDays, String contractBegin, String contractEnd) {
        this.roomId = roomId;
        this.contractId = contractId;
        this.contractNo = contractNo;
        this.apartmentId = apartmentId;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.monthInfo = monthInfo;
        this.rentIn = rentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.rentRebate = rentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentIn = accRentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentRebate = accRentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feesIn = feesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feesRebate = feesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesIn = accFeesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesRebate = accFeesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.isConcentrated = isConcentrated;
        this.city = city;
        this.district = district;
        this.storeorgstructureId = storeorgstructureId;
        this.customerName = customerName;
        this.monthDays = monthDays;
        this.contractBegin = contractBegin;
        this.contractEnd = contractEnd;
    }


    public StateInRentFeeBO_v2(Integer roomId, Integer contractId, String contractNo, String apartmentId, String roomName, String apartmentName, String monthInfo, BigDecimal rentIn, BigDecimal rentRebate, BigDecimal accRentIn, BigDecimal accRentRebate, BigDecimal feesIn, BigDecimal feesRebate, BigDecimal accFeesIn, BigDecimal accFeesRebate, String feePerson) {
        this.roomId = roomId;
        this.contractId = contractId;
        this.contractNo = contractNo;
        this.apartmentId = apartmentId;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.monthInfo = monthInfo;
        this.rentIn = rentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.rentRebate = rentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentIn = accRentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentRebate = accRentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feesIn = feesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feesRebate = feesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesIn = accFeesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesRebate = accFeesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feePerson = feePerson;
    }


    public StateInRentFeeBO_v2(Integer roomId, Integer contractId, String contractNo, String apartmentId, String roomName, String apartmentName, String monthInfo, BigDecimal rentIn, BigDecimal rentRebate, BigDecimal accRentIn, BigDecimal accRentRebate, BigDecimal feesIn, BigDecimal feesRebate, BigDecimal accFeesIn, BigDecimal accFeesRebate, Integer feeTypeId, String feeTypeName, String feePerson) {
        this.roomId = roomId;
        this.contractId = contractId;
        this.contractNo = contractNo;
        this.apartmentId = apartmentId;
        this.roomName = roomName;
        this.apartmentName = apartmentName;
        this.monthInfo = monthInfo;
        this.rentIn = rentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.rentRebate = rentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentIn = accRentIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accRentRebate = accRentRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feeTypeId = feeTypeId;
        this.feeTypeName = feeTypeName;
        this.feesIn = feesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feesRebate = feesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesIn = accFeesIn.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.accFeesRebate = accFeesRebate.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.feePerson = feePerson;
    }


}
