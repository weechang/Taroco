package io.github.weechang.moreco.monitor.sdk.server;

import lombok.Data;

/**
 * @author zhangwei
 * date 2018/12/29
 * time 11:12
 */
@Data
public class MemoryInfo {
    protected long memTotal = 0L;
    protected long memAvailable = 0L;
    protected long swapTotal = 0L;
    protected long swapUsed = 0L;
    protected long swapPagesIn = 0L;
    protected long swapPagesOut = 0L;
    protected long pageSize = 0L;
}
