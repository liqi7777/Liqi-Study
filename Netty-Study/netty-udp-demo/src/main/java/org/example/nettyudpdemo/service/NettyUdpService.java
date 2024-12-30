package org.example.nettyudpdemo.service;

import org.example.nettyudpdemo.entity.NettyUdpApiData;

/**
 * @author liqi
 * create  2024/12/30 3:09 下午
 */
public interface NettyUdpService {
    /**
     * 解析udp数据
     */
    NettyUdpApiData handleUdpData(String msg);
}
