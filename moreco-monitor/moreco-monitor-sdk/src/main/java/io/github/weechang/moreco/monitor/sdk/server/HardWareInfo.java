package io.github.weechang.moreco.monitor.sdk.server;

import lombok.Data;

/**
 * @author zhangwei
 * date 2018/12/29
 * time 11:10
 */
@Data
public class HardWareInfo {

    private CpuInfo cpuInfo;

    private MemoryInfo memoryInfo;

    private DiskInfo diskInfo;
}
