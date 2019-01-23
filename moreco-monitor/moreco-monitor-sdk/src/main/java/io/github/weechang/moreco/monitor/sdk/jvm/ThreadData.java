package io.github.weechang.moreco.monitor.sdk.jvm;

import io.github.weechang.moreco.monitor.sdk.util.MonitorUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 13:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThreadData {

    private String name;
    private long id;
    private int priority;
    private boolean daemon;
    private Thread.State state;
    private long cpuTimeMillis;
    private long userTimeMillis;
    private boolean deadlocked;
    private String globalThreadId;
    private List<StackTraceElement> stackTrace;

    public ThreadData(Thread thread, List<StackTraceElement> stackTrace, long cpuTimeMillis, long userTimeMillis, boolean deadlocked, String instancecode) {
        this.name = thread.getName();
        this.id = thread.getId();
        this.priority = thread.getPriority();
        this.daemon = thread.isDaemon();
        this.state = thread.getState();
        this.cpuTimeMillis = cpuTimeMillis;
        this.userTimeMillis = userTimeMillis;
        this.deadlocked = deadlocked;
        this.globalThreadId = buildGlobalThreadId(thread, instancecode);
    }

    private static String buildGlobalThreadId(Thread thread, String instancecode) {
        return MonitorUtil.getPid() + "_" + instancecode + "_" + thread.getId();
    }
}
