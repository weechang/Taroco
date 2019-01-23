package io.github.weechang.moreco.monitor.sdk.jvm;

import io.github.weechang.moreco.monitor.sdk.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/12/17
 * time 18:01
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class JvmInfo extends MonitorId {

    private Integer pid;
    private long loadedClassCount;
    private long totalLoadedClassCount;
    private long unloadedClassCount;
    private double processCpuLoad;
    private long processCpuTime;
    private double systemCpuLoad;
    private double systemLoadAverage;
    private long heapCommitted;
    private long heapInit;
    private long heapMax;
    private long heapUsed;
    private long nonHeapCommitted;
    private long nonHeapInit;
    private long nonHeapMax;
    private long nonHeapUsed;
    private int threadCount;
    private int threadDaemonCount;
    private int threadPeakCount;
    private long threadTotalCount;
    private List<JvmGcInfo> gcInfo;
    private List<MemoryPoolInfo> memoryPoolList;
    private List<BufferPoolInfo> bufferPoolList;
    private List<ThreadData> threadList;
}
