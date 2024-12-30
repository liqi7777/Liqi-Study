package org.example.nettyudpdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.nettyudpdemo.entity.NettyUdpApiData;
import org.example.nettyudpdemo.service.NettyUdpService;
import org.springframework.stereotype.Service;

/**
 * @author liqi
 * create  2024/12/30 3:10 下午
 */
@Service
@Slf4j
public class NettyUdpServiceImpl implements NettyUdpService {
    @Override
    public NettyUdpApiData handleUdpData(String msg) {
        log.info("===============udp接收数据=================");
        log.info("===upd接收的数据为：{}", msg);
        //todo 根据信息的内容去做具体的事情

        NettyUdpApiData hitUdpApiData = new NettyUdpApiData();
        hitUdpApiData.setMsgData("OK");
        return hitUdpApiData;
    }
}
