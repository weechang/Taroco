package io.github.moreco.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务器
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:16
 */
@Data
public class Server implements Serializable {
    private static final long serialVersionUID = 3179758059178408439L;

    private HardWare hardWare;

    private OS os;
}
