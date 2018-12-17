package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;

/**
 * Top 命令信息
 *
 * @author zhangwei
 * date 2018/12/3
 * time 17:11
 */
@Data
public class TopInfo {

    private TaskInfo taskInfo;
    private CpuInfo cpuInfo;
    private MemInfo memInfo;
    private SwapInfo swapInfo;
}
