package io.github.weechang.moreco.monitor.core.server;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 14:17
 */
@NoArgsConstructor
@Data
public class ServerInfo {

    private String hostname;
    private List<DiskInfo> diskInfos;
    private TaskInfo taskInfo;
    private CpuInfo cpuInfo;
    private MemInfo memInfo;
    private SwapInfo swapInfo;
}
