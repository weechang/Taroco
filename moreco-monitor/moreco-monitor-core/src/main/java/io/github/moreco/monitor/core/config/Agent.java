package io.github.moreco.monitor.core.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * agent 配置
 *
 * @author zhangwei
 * date 2018/12/30
 * time 14:40
 */
@Data
public class Agent implements Serializable {
    private static final long serialVersionUID = -6426885369650608689L;

    private static volatile Agent agent;

    public static Agent getInstance() {
        if (agent == null) {
            synchronized (Agent.class) {
                if (agent == null) {
                    agent = new Agent();
                }
            }
        }
        return agent;
    }

    /**
     * 应用分组
     */
    public String groupKey = null;

    /**
     * 应用key
     */
    public String appKey = null;

    /**
     * Server收集间隔
     */
    public Integer serverRate = 20;

    /**
     * JVM收集间隔
     */
    public Integer jvmRate = 20;

    /**
     * JVM START收集间隔
     */
    public Integer jvmStartRate = 3600;

    /**
     * 心跳间隔
     */
    public Integer heartBeatRate = 5;

    /**
     * 数据发送间隔
     */
    public Integer sendRate = 5;

    /**
     * TCP 服务器节点
     */
    public List<ServerNode> nodes;

    /**
     * kafka topic
     */
    public String topic;
}
