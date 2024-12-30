package org.example.nettyudpdemo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liqi
 * create  2024/12/30 3:11 下午
 */
@Data
public class NettyUdpApiData implements Serializable {
    private static final long serialVersionUID = -5681835145351449218L;

    /**
     * 通讯包类型
     */
    private String msgType;

    /**
     * 不同类型的数据包序列化成字符串到 msgData
     */
    private String msgData;
}
