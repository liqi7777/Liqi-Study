package org.example.nettyudpdemo.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author liqi
 * create  2024/12/30 4:17 下午
 */
public class ClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端连接到服务端后，自动发送消息
        String message = "Hello, Netty UDP Server!";
        DatagramPacket packet = new DatagramPacket(
                Unpooled.copiedBuffer(message, CharsetUtil.UTF_8),
                new InetSocketAddress("127.0.0.1", 8083) // 服务器地址和端口
        );
        ctx.writeAndFlush(packet); // 发送消息
        System.out.println("Sent message: " + message);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        // 处理从服务端接收到的消息
        String received = msg.content().toString(CharsetUtil.UTF_8);
        System.out.println("Received message from server: " + received);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 捕获异常并打印
        cause.printStackTrace();
        ctx.close();
    }
}
