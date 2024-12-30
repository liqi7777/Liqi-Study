package org.example.nettyudpdemo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;

/**
 * @author liqi
 * create  2024/12/30 4:16 下午
 */
@Slf4j
public class NettyUdpClient {


    private final String host;
    private final int port;
    private ChannelFuture channelFuture;

    public NettyUdpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public NettyUdpClient start() throws Exception {
        // 创建Bootstrap对象
        Bootstrap bootstrap = new Bootstrap();

        // 设置EventLoopGroup
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioDatagramChannel.class) // 选择NIO UDP协议
                .handler(new ChannelInitializer<DatagramChannel>() {
                    @Override
                    public void initChannel(DatagramChannel ch) throws Exception {
                        // 这里配置处理UDP消息的编码器和解码器
                        ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8)); // 解析接收到的String消息
                        ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8)); // 将发送的String消息编码成ByteBuf
                        ch.pipeline().addLast(new ClientHandler()); // 客户端的消息处理器
                    }
                });

        // 绑定客户端地址并发送数据
        channelFuture = bootstrap.connect(host, port).sync(); // 连接到服务端

        // 等待连接建立
        channelFuture.channel().closeFuture().addListener(future -> {
            log.info("UDP客户端已关闭");
        });
        return this;
    }

    public void send(String message) {
        try {
            if (this.channelFuture != null && this.channelFuture.channel().isActive()) {
                if (!StringUtils.isEmpty(message)) {
                    log.info("队列消息发送===>{}", message);
                    // 创建 DatagramPacket 并指定目标地址
                    this.channelFuture.channel().writeAndFlush(message);
                    // InetSocketAddress remoteAddress = new InetSocketAddress(host, port);
                    // this.channelFuture.channel().writeAndFlush(
                    //         new io.netty.channel.socket.DatagramPacket(
                    //                 Unpooled.copiedBuffer(message, CharsetUtil.UTF_8),
                    //                 remoteAddress)
                    // );
                }
            }
        } catch (Exception e) {
            log.error("Error while sending message: ", e);
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1"; // 服务端地址
        int port = 8083;           // 服务端端口
        NettyUdpClient nettyUdpClient = new NettyUdpClient(host, port).start();// 启动客户端
        while (true) {
            Thread.sleep(1000);
            nettyUdpClient.send("TEST");
        }
    }

}
