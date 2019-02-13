package io.github.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件分区
 *
 * @author zhangwei
 * date 2018/12/29
 * time 22:36
 */
@Data
public class FileStore implements Serializable {
    private static final long serialVersionUID = -856693592913379042L;

    /**
     * 分区名
     */
    private String name;
    /**
     * 挂载
     */
    private String mount;
    /**
     * 类型
     */
    private String fsType;
    /**
     * 可用空间
     */
    private long usableSpace;
    /**
     * 总空间
     */
    private long totalSpace;
}
