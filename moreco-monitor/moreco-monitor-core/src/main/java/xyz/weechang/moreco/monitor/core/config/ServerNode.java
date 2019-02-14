package xyz.weechang.moreco.monitor.core.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TCP 服务器节点配置
 *
 * @author zhangwei
 * date 2018/12/30
 * time 14:35
 */
@NoArgsConstructor
@Data
public class ServerNode implements Serializable {

    private static final long serialVersionUID = 4329014152381764303L;

    /**
     * ip 地址
     */
    public String host;

    /**
     * 端口
     */
    public int port = 6789;

    public ServerNode(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
