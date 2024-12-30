package org.example.nettyudpdemo.entity;

import lombok.Data;

import java.net.InetSocketAddress;

/**
 * @version 1.0
 * @Description
 * @Author liuhui
 * @email 240971911@qq.com
 * @date 2023/10/17 15:34
 */
@Data
public class Message {
    private String Message;
    private InetSocketAddress sender;
}
