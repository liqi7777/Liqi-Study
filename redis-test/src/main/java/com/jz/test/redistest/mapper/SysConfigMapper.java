package com.jz.test.redistest.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.jz.test.redistest.domain.SysConfig;

/**
 * @author  liqi
 * create  2022/6/27 12:07 下午
 */
@DS("master")
public interface SysConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
}