package org.example.nettyudpdemo.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.example.nettyudpdemo.entity.Message;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @version 1.0
 * @Description
 * @Author liuhui
 * @email 240971911@qq.com
 * @date 2023/10/17 15:32
 */
public class MessageDecoder extends MessageToMessageDecoder<DatagramPacket> {

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) throws Exception {

        InetSocketAddress sender = packet.sender();

        ByteBuf in = packet.content();
        int readableBytes = in.readableBytes();
        if (readableBytes <= 0) {
            return;
        }
        String msg = in.toString(CharsetUtil.UTF_8);
        in.readerIndex(in.readerIndex() + in.readableBytes());

        Message message = new Message();
        message.setMessage(msg);
        message.setSender(sender);

        out.add(message);
    }

}
