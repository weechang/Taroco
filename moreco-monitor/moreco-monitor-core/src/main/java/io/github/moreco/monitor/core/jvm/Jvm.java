package io.github.moreco.monitor.core.jvm;

import io.github.moreco.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 虚拟机
 *
 * @author zhangwei
 * date 2018/12/17
 * time 18:01
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class Jvm extends MonitorId {

    private Integer pid;
    private String mainClass;
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
    private List<JvmGc> gcInfo;
    private List<MemoryPool> memoryPoolList;
    private List<BufferPool> bufferPoolList;
    private List<ThreadData> threadList;
}
