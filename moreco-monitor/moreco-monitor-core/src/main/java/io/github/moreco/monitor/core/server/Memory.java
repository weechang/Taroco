package io.github.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * 内存信息
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:15
 */
@Data
public class Memory implements Serializable {
    private static final long serialVersionUID = 5302218358498531279L;
    private long memTotal = 0L;
    private long memAvailable = 0L;
    private long swapTotal = 0L;
    private long swapUsed = 0L;
    private long swapPagesIn = 0L;
    private long swapPagesOut = 0L;
    private long pageSize = 0L;
}
