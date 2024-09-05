package com.jz.test.redistest.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.jz.test.redistest.domain.ConfigInfo;

/**
 * @author  liqi
 * create  2022/6/27 12:13 下午
 */
@DS("slave")
public interface ConfigInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConfigInfo record);

    int insertSelective(ConfigInfo record);

    ConfigInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConfigInfo record);

    int updateByPrimaryKey(ConfigInfo record);
}