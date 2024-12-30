package org.example.nettyudpdemo.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.example.nettyudpdemo.service.NettyUdpService;

/**
 * @version 1.0
 * @Description
 * @Author liuhui
 * @email 240971911@qq.com
 * @date 2023/10/17 15:28
 */
@Slf4j
public class NettyServer {

    private final EventLoopGroup bossLoopGroup;

    private final ChannelGroup channelGroup;

    private final Class<? extends PipelineFactory> pipelineFactoryClass;

    private NettyUdpService udpService;

    /**
     * Initialize the netty server class
     *
     * @param pipelineFactoryType {@link Class} of the piprline factory type
     */
    public NettyServer(Class<? extends PipelineFactory> pipelineFactoryType, NettyUdpService udpService) {
        // Initialization private members
        this.bossLoopGroup = new NioEventLoopGroup();
        this.channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        this.pipelineFactoryClass = pipelineFactoryType;
        this.udpService = udpService;
    }


    /**
     * Startup the TCP server
     *
     * @param port port of the server
     * @throws Exception if any {@link Exception}
     */
    public final void startup(int port) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossLoopGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.AUTO_CLOSE, false)
                .option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.SO_RCVBUF, 1024 * 1024)// 设置UDP读缓冲区为1M
                .option(ChannelOption.SO_SNDBUF, 1024 * 1024);// 设置UDP写缓冲区为1M;

        PipelineFactory pipelineFactory = pipelineFactoryClass.newInstance();

        @SuppressWarnings("rawtypes")
        ChannelInitializer initializer = pipelineFactory.createInitializer(udpService);

        bootstrap.handler(initializer);

        try {
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelGroup.add(channelFuture.channel());
        } catch (Exception e) {
            shutdown();
            throw e;
        }
    }

    /**
     * Shutdown the server
     *
     * @throws Exception
     */
    public final void shutdown() throws Exception {
        channelGroup.close();
        bossLoopGroup.shutdownGracefully();
        log.info("===========UDP server stutdown==============");
    }
}
