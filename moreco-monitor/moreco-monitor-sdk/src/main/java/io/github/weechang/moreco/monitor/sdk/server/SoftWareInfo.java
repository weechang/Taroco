package io.github.weechang.moreco.monitor.sdk.server;

import lombok.Data;
import oshi.SystemInfo;

/**
 * @author zhangwei
 * date 2018/12/29
 * time 11:11
 */
@Data
public class SoftWareInfo {

    private SystemInfo systemInfo;

    private FileSystem fileSystem;
}
