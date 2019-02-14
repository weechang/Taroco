package xyz.weechang.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 操作系统
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:15
 */
@Data
public class OS implements Serializable {

    /**
     * hostname
     */
    private String hostName;

    /**
     * 默认ip
     */
    private String defaultIp;

    /**
     * 操作系统类型
     */
    private String family;

    /**
     * 版本
     */
    private String version;

    /**
     * 构建号
     */
    private String buildNumber;

    /**
     * 系统位数
     */
    private int bitness = 0;

    /**
     * 线程数
     */
    private int threadCount = 0;

    /**
     * 进程数
     */
    private int processCount = 0;

    /**
     * 文件系统
     */
    private List<FileStore> fileStores;
}
