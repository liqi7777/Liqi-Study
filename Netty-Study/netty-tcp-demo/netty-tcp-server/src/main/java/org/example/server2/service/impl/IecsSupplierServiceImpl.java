package org.example.server2.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.server2.service.IecsSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author liqi
 * create  2023/9/19 3:34 下午
 */
@Service
@Slf4j
public class IecsSupplierServiceImpl implements IecsSupplierService {


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String handler(String msg) {
        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("TCP服务端接收消息处理异常,消息:{},异常信息:{}", msg, ExceptionUtil.stacktraceToString(e, 2000));
            return null;
        }
    }
}
