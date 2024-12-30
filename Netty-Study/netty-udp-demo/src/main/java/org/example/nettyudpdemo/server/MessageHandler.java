package org.example.nettyudpdemo.server;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.nettyudpdemo.entity.Message;
import org.example.nettyudpdemo.entity.NettyUdpApiData;
import org.example.nettyudpdemo.service.NettyUdpService;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Description
 * @Author liuhui
 * @email 240971911@qq.com
 * @date 2023/10/17 15:31
 */
@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<Message> {

    private final static ExecutorService workerThreadService = blockingExecutors(
            Runtime.getRuntime().availableProcessors() * 2);

    private NettyUdpService udpService;

    public MessageHandler(NettyUdpService udpService) {
        this.udpService = udpService;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        handleMessage(ctx, msg);
    }

    /**
     * Actual Message handling and reply to server.
     *
     * @param ctx {@link ChannelHandlerContext}
     * @param msg {@link Message}
     */
    private void handleMessage(ChannelHandlerContext ctx, Message msg) {
        System.out.println("Message Received : " + msg.getMessage());
        boolean release = true;
        try {
            workerThreadService.execute(() -> {
                try {
                    NettyUdpApiData nettyUdpApiData = udpService.handleUdpData(msg.getMessage());
                    if (Objects.nonNull(nettyUdpApiData)) {
                        // ctx.channel().writeAndFlush(nettyUdpApiData).addListener(ChannelFutureListener.CLOSE);
                        // ctx.channel().writeAndFlush(nettyUdpApiData);
                        byte[] responseBytes = JSON.toJSONString(nettyUdpApiData).getBytes(CharsetUtil.UTF_8);
                        DatagramPacket packet = new DatagramPacket(Unpooled.wrappedBuffer(responseBytes), msg.getSender());
                        ctx.channel().writeAndFlush(packet);
                    }
                    log.info("客户端收到服务器数据:{},返回结果:{}", msg, JSON.toJSONString(nettyUdpApiData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } finally {
            if (release) {
                ReferenceCountUtil.release(msg);
            }
        }
    }

    /**
     * {@link WriteListener} is the lister message status interface.
     *
     * @author Sameer Narkhede See <a href="https://narkhedesam.com">https://narkhedesam.com</a>
     * @since Sept 2020
     */
    public interface WriteListener {
        void messageRespond(boolean success);
    }

    private static ExecutorService blockingExecutors(int size) {
        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10000));
    }

}