package xyz.weechang.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * CPU 信息
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:15
 */
@Data
public class Cpu implements Serializable {

    private static final long serialVersionUID = -8893384679282816578L;

    private double lastCpuLoad = 0.0D;
    private long lastCpuLoadTime = 0L;
    private boolean sunMXBean = false;
    /**
     * 逻辑CPU核心数
     */
    protected int logicalProcessorCount = 0;
    /**
     * 物理CPU核心数
     */
    protected int physicalProcessorCount = 0;
    /**
     * CPU packages
     */
    protected int physicalPackageCount = 0;
    private long tickTime;
    private long[] prevTicks;
    private long[] curTicks;
    private long procTickTime;
    private long[][] prevProcTicks;
    private long[][] curProcTicks;
    /**
     * cpu 频率
     */
    private Long cpuVendorFreq;
}
