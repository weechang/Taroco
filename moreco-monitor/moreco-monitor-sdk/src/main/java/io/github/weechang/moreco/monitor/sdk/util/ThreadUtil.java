package io.github.weechang.moreco.monitor.sdk.util;

import io.github.weechang.moreco.monitor.sdk.jvm.ThreadData;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 13:56
 */
public class ThreadUtil {

//    private static final ThreadMXBean THREAD_BEAN = ManagementFactory.getThreadMXBean();
//    private static final boolean CPU_TIME_ENABLED;
//
//    public static long getCurrentThreadCpuTime() {
//        return getThreadCpuTime(Thread.currentThread().getId());
//    }
//
//    public static long getThreadCpuTime(long threadId) {
//        return CPU_TIME_ENABLED ? THREAD_BEAN.getThreadCpuTime(threadId) : 0L;
//    }
//
//    private static long[] getDeadlockedThreads(ThreadMXBean threadBean) {
//        long[] deadlockedThreads;
//        if (threadBean.isSynchronizerUsageSupported()) {
//            deadlockedThreads = threadBean.findDeadlockedThreads();
//        } else {
//            deadlockedThreads = threadBean.findMonitorDeadlockedThreads();
//        }
//
//        if (deadlockedThreads != null) {
//            Arrays.sort(deadlockedThreads);
//        }
//
//        return deadlockedThreads;
//    }
//
//    public static List<ThreadData> buildThreadDataList() {
//        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
//        Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();
//        List<Thread> threads = new ArrayList(stackTraces.keySet());
//        boolean cpuTimeEnabled = threadBean.isThreadCpuTimeSupported() && threadBean.isThreadCpuTimeEnabled();
//        long[] deadlockedThreads = getDeadlockedThreads(threadBean);
//        List<ThreadData> threadInfosList = new ArrayList(threads.size());
////        String hostAddress = MonitorUtil.getJvmInstanceCode();
//        Iterator i$ = threads.iterator();
//
//        while(i$.hasNext()) {
//            Thread thread = (Thread)i$.next();
//            StackTraceElement[] stackTraceElements = (StackTraceElement[])stackTraces.get(thread);
//            List<StackTraceElement> stackTraceElementList = stackTraceElements == null ? null : new ArrayList(Arrays.asList(stackTraceElements));
//            long cpuTimeMillis;
//            long userTimeMillis;
//            if (cpuTimeEnabled) {
//                cpuTimeMillis = threadBean.getThreadCpuTime(thread.getId()) / 1000000L;
//                userTimeMillis = threadBean.getThreadUserTime(thread.getId()) / 1000000L;
//            } else {
//                cpuTimeMillis = -1L;
//                userTimeMillis = -1L;
//            }
//
//            boolean deadlocked = deadlockedThreads != null && Arrays.binarySearch(deadlockedThreads, thread.getId()) >= 0;
//            threadInfosList.add(new ThreadData(thread, stackTraceElementList, cpuTimeMillis, userTimeMillis, deadlocked, hostAddress));
//        }
//
//        return threadInfosList;
//    }
//
//    static {
//        CPU_TIME_ENABLED = THREAD_BEAN.isThreadCpuTimeSupported() && THREAD_BEAN.isThreadCpuTimeEnabled();
//    }
}
