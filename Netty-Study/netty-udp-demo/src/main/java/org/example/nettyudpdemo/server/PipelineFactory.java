package org.example.nettyudpdemo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;
import org.example.nettyudpdemo.service.NettyUdpService;

/**
 * @version 1.0
 * @Description
 * @Author liuhui
 * @email 240971911@qq.com
 * @date 2023/10/17 15:30
 */
public interface PipelineFactory {
    // Socket Channel initializer
    ChannelInitializer<DatagramChannel> createInitializer(NettyUdpService udpService);
}
