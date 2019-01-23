package io.github.weechang.moreco.monitor.sdk.jvm;

import io.github.weechang.moreco.monitor.sdk.common.MonitorId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangwei
 * date 2018/12/17
 * time 18:01
 */
@ApiModel("JVM 启动信息")
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@NoArgsConstructor
public class JvmStartInfo extends MonitorId {

    private Integer pid;
    private String os;
    private String osArch;
    private String osVersion;
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
