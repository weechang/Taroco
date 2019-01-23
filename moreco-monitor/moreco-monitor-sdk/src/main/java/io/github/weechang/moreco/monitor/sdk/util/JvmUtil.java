package io.github.weechang.moreco.monitor.sdk.util;

import com.alibaba.fastjson.JSON;
import com.sun.management.OperatingSystemMXBean;
import io.github.weechang.moreco.monitor.sdk.common.MonitorConfig;
import io.github.weechang.moreco.monitor.sdk.jvm.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * JVM 工具类
 *
 * @author zhangwei
 * date 2018/12/3
 * time 16:19
 */
@Slf4j
public class JvmUtil {

    private static MonitorConfig CONFIG = MonitorConfig.getInstance();

    public static final JvmInfo getJvmInfo() {
        JvmInfo jvmInfo = new JvmInfo();
        jvmInfo.setPid(MonitorUtil.getPid());
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMen = mbean.getHeapMemoryUsage();
        jvmInfo.setHeapInit(heapMen.getInit());
        jvmInfo.setHeapUsed(heapMen.getUsed());
        jvmInfo.setHeapMax(heapMen.getMax());
        jvmInfo.setHeapCommitted(heapMen.getCommitted());
        heapMen = mbean.getNonHeapMemoryUsage();
        jvmInfo.setNonHeapInit(heapMen.getInit());
        jvmInfo.setNonHeapUsed(heapMen.getUsed());
        jvmInfo.setNonHeapMax(heapMen.getMax());
        jvmInfo.setNonHeapCommitted(heapMen.getCommitted());
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        jvmInfo.setThreadDaemonCount(threadMXBean.getDaemonThreadCount());
        jvmInfo.setThreadPeakCount(threadMXBean.getPeakThreadCount());
        jvmInfo.setThreadCount(threadMXBean.getThreadCount());
        jvmInfo.setThreadTotalCount(threadMXBean.getTotalStartedThreadCount());
        List<JvmGcInfo> gcList = new ArrayList();
        List<GarbageCollectorMXBean> gcMXbeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : gcMXbeans){
            String name = garbageCollectorMXBean.getName();
            JvmGcInfo gcInfo = new JvmGcInfo();
            gcInfo.setGcName(name);
            gcInfo.setGcTotalCount(garbageCollectorMXBean.getCollectionCount());
            gcInfo.setGcTotalTime(garbageCollectorMXBean.getCollectionTime());
            gcList.add(gcInfo);
        }
        jvmInfo.setGcInfo(gcList);
        ClassLoadingMXBean classBean = ManagementFactory.getClassLoadingMXBean();
        jvmInfo.setLoadedClassCount((long) classBean.getLoadedClassCount());
        jvmInfo.setTotalLoadedClassCount(classBean.getTotalLoadedClassCount());
        jvmInfo.setUnloadedClassCount(classBean.getUnloadedClassCount());
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        jvmInfo.setProcessCpuLoad(osmxb.getProcessCpuLoad());
        jvmInfo.setSystemCpuLoad(osmxb.getSystemCpuLoad());
        jvmInfo.setProcessCpuTime(osmxb.getProcessCpuTime());

        try {
            List<BufferPoolMXBean> bufferPools = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
            if (bufferPools != null && bufferPools.size() > 0) {
                List<BufferPoolInfo> bufferPoolList = new ArrayList(bufferPools.size());
                for (BufferPoolMXBean bp : bufferPools){
                    BufferPoolInfo bpInfo = new BufferPoolInfo();
                    bpInfo.setCount(bp.getCount());
                    bpInfo.setMemoryUsed(bp.getMemoryUsed());
                    bpInfo.setTotalCapacity(bp.getTotalCapacity());
                    bpInfo.setPoolName(bp.getName());
                    bufferPoolList.add(bpInfo);
                }
                jvmInfo.setBufferPoolList(bufferPoolList);
            }

            jvmInfo.setSystemLoadAverage(osmxb.getSystemLoadAverage());
            List<MemoryPoolMXBean> listPool = ManagementFactory.getMemoryPoolMXBeans();
            List<MemoryPoolInfo> memoryPoolList = new ArrayList();

            MemoryPoolInfo memoryInfo;
            for (MemoryPoolMXBean bean : listPool) {
                MemoryUsage usage = bean.getUsage();
                memoryInfo = new MemoryPoolInfo();
                memoryInfo.setCommitted(usage.getCommitted());
                memoryInfo.setMax(usage.getMax());
                memoryInfo.setUsed(usage.getUsed());
                memoryInfo.setMemoryPoolName(bean.getName());
                memoryInfo.setType(bean.getType());
                memoryInfo.setInit(usage.getInit());
                MemoryUsage lastUsage = bean.getCollectionUsage();
                if (lastUsage != null) {
                    MemoryPoolInfo lastMemoryInfo = new MemoryPoolInfo();
                    lastMemoryInfo.setCommitted(lastUsage.getCommitted());
                    lastMemoryInfo.setMax(lastUsage.getMax());
                    lastMemoryInfo.setUsed(lastUsage.getUsed());
                    lastMemoryInfo.setMemoryPoolName(bean.getName());
                    lastMemoryInfo.setType(bean.getType());
                    lastMemoryInfo.setInit(lastUsage.getInit());
                    memoryInfo.setLastMemoryPoolInfo(lastMemoryInfo);
                }
                memoryPoolList.add(memoryInfo);
            }
            jvmInfo.setMemoryPoolList(memoryPoolList);
//            jvmInfo.setThreadList(ThreadUtil.buildThreadDataList());
            log.debug("thread info :{}", JSON.toJSONString(jvmInfo.getThreadList()));
        } catch (Exception var19) {
            jvmInfo.setSystemLoadAverage(-1.0D);
        }

        return jvmInfo;
    }

    public static JvmStartInfo getJvmStartInfo() {
        JvmStartInfo jvmStartInfo = new JvmStartInfo();
        jvmStartInfo.setPid(MonitorUtil.getPid());
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        jvmStartInfo.setTotalPhysicalMemorySize(osmxb.getTotalPhysicalMemorySize());
        jvmStartInfo.setTotalSwapSpaceSize(osmxb.getTotalSwapSpaceSize());
        jvmStartInfo.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        jvmStartInfo.setOs(System.getProperty("os.name"));
        jvmStartInfo.setOsArch(System.getProperty("os.arch"));
        jvmStartInfo.setOsVersion(System.getProperty("os.version"));
        jvmStartInfo.setJavaVersion(System.getProperty("java.version"));
        jvmStartInfo.setJavaVendor(System.getProperty("java.vendor"));
        jvmStartInfo.setJavaHome(System.getProperty("java.home"));
        jvmStartInfo.setUserDir(System.getProperty("user.dir"));
        jvmStartInfo.setUserName(System.getProperty("user.name"));
        jvmStartInfo.setUserHome(System.getProperty("user.home"));
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        jvmStartInfo.setBootClassPath(runtimeMXBean.getBootClassPath());
        jvmStartInfo.setClassPath(runtimeMXBean.getClassPath());
        jvmStartInfo.setLibPath(runtimeMXBean.getLibraryPath());
        jvmStartInfo.setArguments(runtimeMXBean.getInputArguments().toString());
        jvmStartInfo.setStartTime(runtimeMXBean.getStartTime());
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        jvmStartInfo.setYoungGC(((GarbageCollectorMXBean) gcList.get(0)).getName());
        jvmStartInfo.setFullGC(((GarbageCollectorMXBean) gcList.get(1)).getName());
        Integer tomcatPort = MonitorUtil.getTomcatPortByMBean();
        if (tomcatPort != null) {
            jvmStartInfo.setWebConnector(true);
            jvmStartInfo.setWebPort(tomcatPort);
        }

        return jvmStartInfo;
    }

    public static final Long getStartTime() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return runtimeMXBean.getStartTime();
    }
}
