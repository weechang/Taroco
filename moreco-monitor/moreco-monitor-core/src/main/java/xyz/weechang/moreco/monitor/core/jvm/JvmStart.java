package xyz.weechang.moreco.monitor.core.jvm;

import xyz.weechang.moreco.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * JVM 启动信息
 *
 * @author zhangwei
 * date 2018/12/17
 * time 18:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class JvmStart extends MonitorId {

    private static final long serialVersionUID = 6007986501441858737L;
    private Integer pid;
    private String mainClass;
    private String javaVersion;
    private String javaVendor;
    private String javaHome;
    private int availableProcessors;
    private long totalPhysicalMemorySize;
    private long totalSwapSpaceSize;
    private long startTime;
    private String classPath;
    private String libPath;
    private String bootClassPath;
    private String userDir;
    private String userName;
    private String userHome;
    private String youngGC;
    private String fullGC;
    private String arguments;
    private boolean webConnector = false;
    private Integer webPort;
}
