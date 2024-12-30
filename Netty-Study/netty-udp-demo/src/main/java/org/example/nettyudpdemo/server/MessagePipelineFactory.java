package org.example.nettyudpdemo.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.DatagramChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.example.nettyudpdemo.service.NettyUdpService;

/**
 * @version 1.0
 * @Description
 * @Author liuhui
 * @email 240971911@qq.com
 * @date 2023/10/17 15:32
 */
public class MessagePipelineFactory implements PipelineFactory {
    private final int availableProcessors;
    private final EventExecutorGroup executors;


    /**
     * Constructor fott {@link MessagePipelineFactory}
     */
    public MessagePipelineFactory() {
        availableProcessors = Runtime.getRuntime().availableProcessors();
        executors = new DefaultEventExecutorGroup(availableProcessors);
    }

    /**
     * Pipeline Factory method for channel initialization
     */
    @Override
    public ChannelInitializer<DatagramChannel> createInitializer(NettyUdpService udpService) {

        return new ChannelInitializer<DatagramChannel>() {

            @Override
            protected void initChannel(DatagramChannel ch) throws Exception {
                // Create chanel pipeline
                ChannelPipeline pipeline = ch.pipeline();

                final MessageDecoder decoder = new MessageDecoder();

                pipeline.addLast("decoder", decoder);

                final MessageHandler handler = new MessageHandler(udpService);

                pipeline.addLast(executors, "handler", handler);

            }

        };
    }


}
