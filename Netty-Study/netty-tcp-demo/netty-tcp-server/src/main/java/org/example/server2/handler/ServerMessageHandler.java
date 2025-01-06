package org.example.server2.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.server2.service.IecsSupplierService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerMessageHandler extends ChannelInboundHandlerAdapter {

    private final static ExecutorService workerThreadService = blockingExecutors(
            Runtime.getRuntime().availableProcessors() * 2);

    private IecsSupplierService iecsSupplierService;

    public ServerMessageHandler() {
        super();
    }

    public ServerMessageHandler(IecsSupplierService iecsSupplierService) {
        this.iecsSupplierService = iecsSupplierService;
    }

    /**
     * 功能：读取服务器发送过来的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        boolean release = true;
        try {
            String message = (String) msg;
            workerThreadService.execute(() -> {
                try {
                    String resp = handler(message);
                    if (resp != null) {
                        ctx.channel().writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
                    }
                    log.info("客户端收到服务器数据:{},返回结果:{}", msg, resp);
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
     * 功能：读取完毕客户端发送过来的数据之后的操作
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端接收数据完毕..");
    }


    /**
     * 功能：服务端发生异常的操作
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }

    public IecsSupplierService getIecsSupplierService() {
        return iecsSupplierService;
    }

    public void setIecsSupplierService(IecsSupplierService iecsSupplierService) {
        this.iecsSupplierService = iecsSupplierService;
    }

    private String handler(String msg) throws Exception {
        return iecsSupplierService.handler(msg);
    }

    private static ExecutorService blockingExecutors(int size) {
        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10000));
    }
}
