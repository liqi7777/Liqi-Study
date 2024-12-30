package org.example.nettyudpdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.example.nettyudpdemo.server.MessagePipelineFactory;
import org.example.nettyudpdemo.server.NettyServer;
import org.example.nettyudpdemo.service.NettyUdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @version 1.0
 * @Description
 * @Author liuhui
 * @email 240971911@qq.com
 * @date 2023/10/17 15:18
 */
@Configuration
@Slf4j
public class NettyUdpConfig {

    @Value("${netty.udp.port:8888}")
    private int udpPort;

    @Autowired
    private NettyUdpService udpService;

    private NettyServer nettyServer;

    @PostConstruct
    public void initUdpServer() {
        try {
            log.info("Starting up the UDP server on port {} ...", udpPort);
            nettyServer = new NettyServer(MessagePipelineFactory.class, udpService);
            nettyServer.startup(udpPort);
            log.info("UDP server started on port {} ...", udpPort);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void stop() throws Exception {
        if (nettyServer != null) {
            nettyServer.shutdown();
        }
    }
}
