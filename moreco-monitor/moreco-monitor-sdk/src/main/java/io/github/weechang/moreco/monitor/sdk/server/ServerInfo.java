package io.github.weechang.moreco.monitor.sdk.server;

import lombok.Data;
import oshi.SystemInfo;

/**
 * @author zhangwei
 * date 2018/12/29
 * time 11:10
 */
@Data
public class ServerInfo {

    private SoftWareInfo softWareInfo;

    private HardWareInfo hardWareInfo;

}
