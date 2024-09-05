package com.jz.test.redistest.test.resetField;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import com.jz.iecs.entity.DTO.IecsOperateInstructionsDschDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
public class IecsOperateInstructionsDschVO extends IecsOperateInstructionsDschDO {
    private static final long serialVersionUID = -4769446553140564277L;

    private String shipName;

    /**
     * 船舶代码
     */
    private String scdVslCd;
    /**
     * 进口航次
     */
    private String scdIvoyage;
    /**
     * 出口航次
     */
    private String scdEvoyage;
    /**
     * 船中文名
     */
    private String vslCnname;
    /**
     * 船英文名
     */
    private String vslEnname;

    /**
     * 状态为：P|T|L的任务
     */
    private String oisOpstatusUn;

    private String startTime;
    private String endTime;

    private List<String> cntrNoList;
    private List<String> truckNoList;
    /**
     * 任务指令状态集合
     */
    private String oisOpstatusBatch;

    /**
     * 处理状态 1：成功
     */
    private Integer upStauts;

    /**
     * 作业路编号
     */
    private String routeNo;
    //超时时间
    private String insertOutTime;

    // upStatus <> 1 and <> 2
    private Integer upStatusNotIn;

    private int anInt;


    public static void main(String[] args) {
        IecsOperateInstructionsDschVO iecsOperateInstructionsDschVO = new IecsOperateInstructionsDschVO();
        iecsOperateInstructionsDschVO.setRouteNo("100");
        iecsOperateInstructionsDschVO.setOisShipno(100L);
        iecsOperateInstructionsDschVO.setAnInt(2);
        iecsOperateInstructionsDschVO.setCntrNoList(Lists.newArrayList());
        iecsOperateInstructionsDschVO = byMethod(iecsOperateInstructionsDschVO);
        System.out.println("haha");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusMinutes(4);
        System.out.println(localDateTime);

        Integer ahah=0;
        CheckTosDataJavaCodeDTO checkTosDataJavaCodeDTO = new CheckTosDataJavaCodeDTO();
        checkTosDataJavaCodeDTO.setN_taskcount(ahah);
        testhahaah(checkTosDataJavaCodeDTO);
        System.out.println(checkTosDataJavaCodeDTO.getN_taskcount());

        System.out.println(DateUnit.MINUTE.getMillis());
    }

    private static void testhahaah(CheckTosDataJavaCodeDTO ahah11) {
        ahah11.setN_taskcount(5);
    }

    public static <T> T byMethod(T t) {
        Method[] sets = ReflectUtil.getMethods(t.getClass(), method -> Objects.requireNonNull(method).getName().indexOf("set") == 0);
        Arrays.stream(ReflectUtil.getMethods(t.getClass(), method -> Objects.requireNonNull(method).getName().indexOf("set") == 0)).forEach(method -> {
            try {
                method.invoke(t, new Object[]{null});
            } catch (Exception e) {
            }
        });
        return t;
    }
}
