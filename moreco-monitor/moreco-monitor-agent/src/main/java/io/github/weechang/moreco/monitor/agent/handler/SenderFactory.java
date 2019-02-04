package io.github.weechang.moreco.monitor.agent.handler;

import io.github.weechang.mcm.core.config.Agent;
import org.apache.commons.lang.StringUtils;

/**
 * 发送器 工厂类
 *
 * @author zhangwei
 * date 2018/12/29
 * time 23:10
 */
public class SenderFactory {

    public static Sender getSender() {
        Sender sender = null;
        if (StringUtils.isNotBlank(Agent.getInstance().topic)) {
            // kafka
            sender = KafkaSender.getInstance();
        } else {
            // tpc
            sender = TpcSender.getInstance();
        }
        return sender;
    }
}
