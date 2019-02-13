package io.github.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * 网络信息
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:15
 */
@Data
public class NetWork implements Serializable {

    /**
     * mac地址
     */
    private String mac;
    /**
     * ipv4
     */
    private String[] ipv4;
    /**
     * ipv6
     */
    private String[] ipv6;
    /**
     * 入网流量
     */
    private long bytesRecv;
    /**
     * 出网流量
     */
    private long bytesSent;
    /**
     * 入网包
     */
    private long packetsRecv;
    /**
     * 出网包
     */
    private long packetsSent;
    /**
     * 入网丢包
     */
    private long inErrors;
    /**
     * 出网丢包
     */
    private long outErrors;
    /**
     * 传输速度
     */
    private long speed;
}
