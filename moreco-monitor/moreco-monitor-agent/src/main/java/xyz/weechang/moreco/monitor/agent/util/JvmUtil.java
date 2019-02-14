package xyz.weechang.moreco.monitor.agent.util;

import com.sun.management.OperatingSystemMXBean;
import xyz.weechang.moreco.monitor.agent.McmAgentApplication;
import sun.management.ConnectorAddressLink;
import xyz.weechang.moreco.monitor.core.jvm.*;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.*;
import java.util.*;

/**
 * JVM 数据搜集工具类
 *
 * @author zhangwei
 * date 2018/12/29
 * time 14:35
 */
@Slf4j
public class JvmUtil {

    public static final Jvm getJvm(JvmProcess process) {
        int pid = process.getPid();
        String mainClass = process.getMainClass();
        boolean current = process.isCurrent();
        Jvm jvm = new Jvm();
        jvm.setPid(pid);
        jvm.setMainClass(mainClass);
        MBeanServerConnection mbsc = null;
        if (!current) {
            mbsc = getMBeanServerConnectionByPid(pid);
        }
        MemoryMXBean mbean = null;
        ThreadMXBean threadBean = null;
        List<GarbageCollectorMXBean> gcBeans = null;
        ClassLoadingMXBean classBean = null;
        OperatingSystemMXBean osBean = null;
        List<BufferPoolMXBean> bpBeans = null;
        List<MemoryPoolMXBean> mpBeans = null;
        try {
            if (current) {
                mbean = ManagementFactory.getMemoryMXBean();
            } else {
                mbean = ManagementFactory.getPlatformMXBean(mbsc, MemoryMXBean.class);
            }
        } catch (Exception e) {
            log.error("get MemoryMXBean error", e);
        }
        try {
            if (current) {
                threadBean = ManagementFactory.getThreadMXBean();
            } else {
                threadBean = ManagementFactory.getPlatformMXBean(mbsc, ThreadMXBean.class);
            }
        } catch (Exception e) {
            log.error("get ThreadMXBean error", e);
        }
        try {
            if (current) {
                gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
            } else {
                gcBeans = ManagementFactory.getPlatformMXBeans(mbsc, GarbageCollectorMXBean.class);
            }
        } catch (Exception e) {
            log.error("get GarbageCollectorMXBean error", e);
        }
        try {
            if (current) {
                classBean = ManagementFactory.getClassLoadingMXBean();
            } else {
                classBean = ManagementFactory.getPlatformMXBean(mbsc, ClassLoadingMXBean.class);
            }
        } catch (Exception e) {
            log.error("get ClassLoadingMXBean error", e);
        }
        try {
            if (current) {
                osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            } else {
                osBean = ManagementFactory.getPlatformMXBean(mbsc, OperatingSystemMXBean.class);
            }
        } catch (Exception e) {
            log.error("get OperatingSystemMXBean error", e);
        }
        try {
            if (current) {
                bpBeans = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
            } else {
                bpBeans = ManagementFactory.getPlatformMXBeans(mbsc, BufferPoolMXBean.class);
            }
        } catch (Exception e) {
            log.error("get BufferPoolMXBean error", e);
        }
        try {
            if (current) {
                mpBeans = ManagementFactory.getMemoryPoolMXBeans();
            } else {
                mpBeans = ManagementFactory.getPlatformMXBeans(mbsc, MemoryPoolMXBean.class);
            }
        } catch (Exception e) {
            log.error("get MemoryPoolMXBean error", e);
        }
        if (mbean != null) {
            MemoryUsage heapMen = mbean.getHeapMemoryUsage();
            jvm.setHeapInit(heapMen.getInit());
            jvm.setHeapUsed(heapMen.getUsed());
            jvm.setHeapMax(heapMen.getMax());
            jvm.setHeapCommitted(heapMen.getCommitted());
            MemoryUsage nonHeapMen = mbean.getNonHeapMemoryUsage();
            jvm.setNonHeapInit(nonHeapMen.getInit());
            jvm.setNonHeapUsed(nonHeapMen.getUsed());
            jvm.setNonHeapMax(nonHeapMen.getMax());
            jvm.setNonHeapCommitted(nonHeapMen.getCommitted());
        }
        if (threadBean != null) {
            jvm.setThreadDaemonCount(threadBean.getDaemonThreadCount());
            jvm.setThreadPeakCount(threadBean.getPeakThreadCount());
            jvm.setThreadCount(threadBean.getThreadCount());
            jvm.setThreadTotalCount(threadBean.getTotalStartedThreadCount());
        }
        if (CollectionUtil.isNotEmpty(gcBeans)) {
            List<JvmGc> gcList = new ArrayList<>();
            for (GarbageCollectorMXBean garbageCollectorMXBean : gcBeans) {
                String name = garbageCollectorMXBean.getName();
                JvmGc gcInfo = new JvmGc();
                gcInfo.setGcName(name);
                gcInfo.setGcTotalCount(garbageCollectorMXBean.getCollectionCount());
                gcInfo.setGcTotalTime(garbageCollectorMXBean.getCollectionTime());
                gcList.add(gcInfo);
            }
            jvm.setGcInfo(gcList);
        }
        if (classBean != null) {
            jvm.setLoadedClassCount((long) classBean.getLoadedClassCount());
            jvm.setTotalLoadedClassCount(classBean.getTotalLoadedClassCount());
            jvm.setUnloadedClassCount(classBean.getUnloadedClassCount());
        }
        if (osBean != null) {
            jvm.setProcessCpuLoad(osBean.getProcessCpuLoad());
            jvm.setSystemCpuLoad(osBean.getSystemCpuLoad());
            jvm.setProcessCpuTime(osBean.getProcessCpuTime());
            jvm.setSystemLoadAverage(osBean.getSystemLoadAverage());
        }
        if (CollectionUtils.isNotEmpty(bpBeans)) {
            List<BufferPool> bufferPoolList = new ArrayList<>(bpBeans.size());
            for (BufferPoolMXBean bp : bpBeans) {
                BufferPool bufferPool = new BufferPool();
                bufferPool.setCount(bp.getCount());
                bufferPool.setMemoryUsed(bp.getMemoryUsed());
                bufferPool.setTotalCapacity(bp.getTotalCapacity());
                bufferPool.setPoolName(bp.getName());
                bufferPoolList.add(bufferPool);
            }
            jvm.setBufferPoolList(bufferPoolList);
        }
        if (CollectionUtils.isNotEmpty(mpBeans)) {
            List<MemoryPool> memoryPoolList = new ArrayList<>();
            MemoryPool memory;
            for (MemoryPoolMXBean mp : mpBeans) {
                MemoryUsage usage = mp.getUsage();
                memory = new MemoryPool();
                memory.setCommitted(usage.getCommitted());
                memory.setMax(usage.getMax());
                memory.setUsed(usage.getUsed());
                memory.setMemoryPoolName(mp.getName());
                memory.setType(mp.getType());
                memory.setInit(usage.getInit());
                MemoryUsage lastUsage = mp.getCollectionUsage();
                if (lastUsage != null) {
                    MemoryPool lastMemory = new MemoryPool();
                    lastMemory.setCommitted(lastUsage.getCommitted());
                    lastMemory.setMax(lastUsage.getMax());
                    lastMemory.setUsed(lastUsage.getUsed());
                    lastMemory.setMemoryPoolName(mp.getName());
                    lastMemory.setType(mp.getType());
                    lastMemory.setInit(lastUsage.getInit());
                    memory.setLastMemoryPoolInfo(lastMemory);
                }
                memoryPoolList.add(memory);
            }
            jvm.setMemoryPoolList(memoryPoolList);
        }

        try {
            jvm.setThreadList(ThreadUtil.getThreadDatas());
        } catch (Exception e) {
            jvm.setSystemLoadAverage(-1.0D);
        }

        jvm.setInstanceCode(getInstanceCode(process));

        return jvm;
    }

    public static JvmStart getJvmStart(JvmProcess process) {
        int pid = process.getPid();
        String mainClass = process.getMainClass();
        boolean current = process.isCurrent();
        JvmStart jvmStart = new JvmStart();
        jvmStart.setPid(pid);
        jvmStart.setMainClass(mainClass);
        MBeanServerConnection mbsc = getMBeanServerConnectionByPid(pid);
        OperatingSystemMXBean osBean = null;
        RuntimeMXBean runtimeBean = null;
        List<GarbageCollectorMXBean> gcBeans = null;
        try {
            if (current) {
                osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            } else {
                osBean = ManagementFactory.getPlatformMXBean(mbsc, OperatingSystemMXBean.class);
            }
        } catch (Exception e) {
            log.error("get OperatingSystemMXBean error", e);
        }
        try {
            if (current) {
                runtimeBean = ManagementFactory.getRuntimeMXBean();
            } else {
                runtimeBean = ManagementFactory.newPlatformMXBeanProxy(mbsc, ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class);
            }
        } catch (Exception e) {
            log.error("get RuntimeMXBean error", e);
        }
        try {
            if (current) {
                gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
            } else {
                gcBeans = ManagementFactory.getPlatformMXBeans(mbsc, GarbageCollectorMXBean.class);
            }
        } catch (Exception e) {
            log.error("get GarbageCollectorMXBean error", e);
        }
        if (osBean != null) {
            jvmStart.setTotalPhysicalMemorySize(osBean.getTotalPhysicalMemorySize());
            jvmStart.setTotalSwapSpaceSize(osBean.getTotalSwapSpaceSize());
            jvmStart.setAvailableProcessors(Runtime.getRuntime().availableProcessors());
        }
        jvmStart.setJavaVersion(System.getProperty("java.version"));
        jvmStart.setJavaVendor(System.getProperty("java.vendor"));
        jvmStart.setJavaHome(System.getProperty("java.home"));
        jvmStart.setUserDir(System.getProperty("user.dir"));
        jvmStart.setUserName(System.getProperty("user.name"));
        jvmStart.setUserHome(System.getProperty("user.home"));
        if (runtimeBean != null) {
            jvmStart.setBootClassPath(runtimeBean.getBootClassPath());
            jvmStart.setClassPath(runtimeBean.getClassPath());
            jvmStart.setLibPath(runtimeBean.getLibraryPath());
            jvmStart.setArguments(runtimeBean.getInputArguments().toString());
            jvmStart.setStartTime(runtimeBean.getStartTime());
        }
        if (CollectionUtils.isNotEmpty(gcBeans)) {
            jvmStart.setYoungGC((gcBeans.get(0)).getName());
            jvmStart.setFullGC((gcBeans.get(1)).getName());
        }
        Integer tomcatPort = getTomcatPort(process);
        if (tomcatPort != null) {
            jvmStart.setWebConnector(true);
            jvmStart.setWebPort(tomcatPort);
        }

        jvmStart.setInstanceCode(getInstanceCode(process));
        return jvmStart;
    }

    public static Integer getTomcatPort(JvmProcess process) {
        int pid = process.getPid();
        boolean current = process.isCurrent();
        Integer tomcatPort = null;
        try {
            MBeanServerConnection connection = null;
            if (current) {
                connection = ManagementFactory.getPlatformMBeanServer();
            } else {
                connection = getMBeanServerConnectionByPid(pid);
            }
            if (connection != null) {
                Set<ObjectName> objectNames = null;
                Set<ObjectName> catalinaObjectNames = connection.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
                if (CollectionUtils.isNotEmpty(catalinaObjectNames)) {
                    objectNames = catalinaObjectNames;
                } else {
                    Set<ObjectName> tomcatObjectNames = connection.queryNames(new ObjectName("Tomcat:type=Connector,*"), null);
                    if (CollectionUtils.isNotEmpty(tomcatObjectNames)) {
                        objectNames = tomcatObjectNames;
                    }
                }
                if (CollectionUtils.isNotEmpty(objectNames)) {
                    for (ObjectName item : objectNames) {
                        String protocol = (String) connection.getAttribute(item, "protocol");
                        if (protocol != null) {
                            protocol = protocol.toLowerCase();
                            if (protocol.startsWith("http")) {
                                tomcatPort = (Integer) connection.getAttribute(item, "port");
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("get tomcatPort error,", e);
        }
        return tomcatPort;
    }

    private static MBeanServerConnection getMBeanServerConnectionByPid(int pid) {
        MBeanServerConnection mbsc = null;
        try {
            String address = ConnectorAddressLink.importFrom(pid);
            if (address != null) {
                JMXServiceURL jmxServiceURL = new JMXServiceURL(address);
                JMXConnector jmxc = JMXConnectorFactory.connect(jmxServiceURL, null);
                if (jmxc != null) {
                    mbsc = jmxc.getMBeanServerConnection();
                }
            }
        } catch (Exception e) {
            log.error("getMBeanServerConnectionByPid is failed,", e);
        }
        return mbsc;
    }

    public static final Long getStartTime(int pid) {
        Long startTime = null;
        MBeanServerConnection mbsc = getMBeanServerConnectionByPid(pid);
        RuntimeMXBean runtimeBean = null;
        try {
            runtimeBean = ManagementFactory.getPlatformMXBean(mbsc, RuntimeMXBean.class);
        } catch (Exception e) {
            log.error("get RuntimeMXBean error", e);
        }
        if (runtimeBean != null) {
            startTime = runtimeBean.getStartTime();
        }
        return startTime;
    }

    public static List<JvmProcess> getJvmProcesses() {
        List<JvmProcess> processes = new ArrayList<>();
        JvmProcess currentProcess = JvmUtil.getCurrentProcess();
        try {
            MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
            HashSet<Integer> vmList = (HashSet<Integer>) local.activeVms();
            for (Integer pid : vmList) {
                MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + pid));
                String mainClass = MonitoredVmUtil.mainClass(vm, true);
                // 当前应用或者开启jmxRemote的才返回，排除监控应用本身
                String address = ConnectorAddressLink.importFrom(pid);
                if ((currentProcess.getPid() == pid || StringUtils.isNotBlank(address))
                        && !McmAgentApplication.class.getName().equals(mainClass)) {
                    boolean current = false;
                    if (pid == currentProcess.getPid()) {
                        current = true;
                    }
                    JvmProcess process = new JvmProcess(pid, mainClass, current);
                    processes.add(process);
                }
            }
        } catch (Exception e) {
            log.error("get all java process error", e);
        }
        return processes;
    }

    public static JvmProcess getCurrentProcess() {
        JvmProcess process = null;
        try {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            String[] processArray = name.split("@");
            Integer pId = Integer.valueOf(processArray[0]);
            MonitoredHost local = MonitoredHost.getMonitoredHost("localhost");
            MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + pId));
            String mainClass = MonitoredVmUtil.mainClass(vm, true);
            process = new JvmProcess(pId, mainClass, true);
        } catch (Exception e) {
            log.error("get now Process error", e);
        }
        return process;
    }

    public static String getInstanceCode(JvmProcess process) {
        String instanceCode = null;
        String defaultIp = ServerUtil.getDefaultIp();
        Integer tomcatPort = getTomcatPort(process);
        if (tomcatPort != null) {
            instanceCode = defaultIp + ":" + tomcatPort;
        } else {
            Integer pid = process.getPid();
            boolean current = process.isCurrent();
            try {
                RuntimeMXBean runtimeBean = null;
                if (current) {
                    runtimeBean = ManagementFactory.getRuntimeMXBean();
                } else {
                    MBeanServerConnection conn = getMBeanServerConnectionByPid(pid);
                    if (conn != null) {
                        runtimeBean = ManagementFactory.newPlatformMXBeanProxy(conn, ManagementFactory.RUNTIME_MXBEAN_NAME, RuntimeMXBean.class);
                    }
                }
                if (runtimeBean != null) {
                    Map<String, String> pro = runtimeBean.getSystemProperties();
                    Map<String, String> targetPro = new HashMap<>();
                    targetPro.put("java.class.path", pro.get("java.class.path"));
                    targetPro.put("sun.java.command", pro.get("sun.java.command"));
                    targetPro.put("client.logRoot", pro.get("client.logRoot"));
                    targetPro.put("user.home", pro.get("user.home"));
                    targetPro.put("sun.boot.class.path", pro.get("sun.boot.class.path"));
                    targetPro.put("java.util.logging.config.file", pro.get("java.io.github.weechang.moreco.monitor.agent.util.logging.config.file"));
                    targetPro.put("dynamic.log.path", pro.get("dynamic.log.path"));
                    targetPro.put("common.loader", pro.get("common.loader"));
                    targetPro.put("java.io.tmpdir", pro.get("java.io.tmpdir"));
                    targetPro.put("user.dir", pro.get("user.dir"));
                    targetPro.put("java.library.path", pro.get("java.library.path"));
                    String path = JSON.toJSONString(targetPro);
                    instanceCode = ServerUtil.getDefaultIp() + ":" + Math.abs(path.hashCode());
                }
            } catch (Exception e) {
                log.error("get instance code error", e);
            }
        }
        return instanceCode;
    }

}
