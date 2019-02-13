package io.github.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * 磁盘信息
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:15
 */
@Data
public class Disk implements Serializable {
    private static final long serialVersionUID = 5828396136961942025L;

    private long size = 0L;
    private long reads = 0L;
    private long readBytes = 0L;
    private long writes = 0L;
    private long writeBytes = 0L;
    private long currentQueueLength = 0L;
    private long transferTime = 0L;
}
