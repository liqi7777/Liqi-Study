package org.example.server2.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.example.server2.handler.MessageCodec;
import org.example.server2.handler.ServerMessageHandler;
import org.example.server2.service.IecsSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

@Configuration
public class NettyConfig {

    @Value("${netty.boss.thread.count}")
    private int bossCount;

    @Value("${netty.worker.thread.count}")
    private int workerCount;

    @Value("${netty.tcp.port}")
    private int tcpPort;

    @Value("${netty.so.backlog}")
    private int backlog;

    @Value("${netty.tcp.bufferSize}")
    private int receiveBufferSize;

    @Autowired
    private IecsSupplierService iecsSupplierService;

    @Bean(name = "bossThreadFactory")
    public NamedThreadFactory bossThreadFactory() {
        return new NamedThreadFactory("Server-Worker");
    }

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount, bossThreadFactory());
    }

    @Bean(name = "workerThreadFactory")
    public NamedThreadFactory workerThreadFactory() {
        return new NamedThreadFactory("Server-Worker");
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerCount, workerThreadFactory());
    }

    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup(), workerGroup()).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, backlog)
                .option(ChannelOption.AUTO_CLOSE, false)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("stringEncoder", new StringEncoder(Charset.forName("GBK")))
//								.addLast("frameDecoder", new MsgLengthFieldBasedFrameDecoder(receiveBufferSize, 0, 6, 0, 6))
                                .addLast("stringDecoder", new StringDecoder(Charset.forName("GBK")))
                                .addLast("messageCodec", new MessageCodec())
                                .addLast("messageHandler", new ServerMessageHandler(iecsSupplierService));

                    }
                });
        return bootstrap;

    }

    @Bean(name = "tcpSocketAddress")
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(tcpPort);
    }
}
