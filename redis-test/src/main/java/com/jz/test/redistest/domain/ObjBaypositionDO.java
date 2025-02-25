package com.jz.test.redistest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0 
 * @Description 描述 
 * @author 在此处备注你的姓名简称 
 * @date 2021-05-07 
 */ 
@Data
@EqualsAndHashCode(callSuper=true)
public class ObjBaypositionDO extends QueryCondition {

    private static final long serialVersionUID = 1L;

    //null
    private String id;

    private String area;

    private String bayno;

    private Long frame;

    private String lvid;

    private String projpt;

}