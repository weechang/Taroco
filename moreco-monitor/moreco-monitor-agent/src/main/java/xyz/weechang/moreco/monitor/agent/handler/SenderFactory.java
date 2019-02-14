package xyz.weechang.moreco.monitor.agent.handler;


import cn.hutool.core.util.StrUtil;
import xyz.weechang.moreco.monitor.core.config.Agent;

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
        if (StrUtil.isNotBlank(Agent.getInstance().topic)) {
            // kafka
            sender = KafkaSender.getInstance();
        } else {
            // tpc
            sender = TpcSender.getInstance();
        }
        return sender;
    }
}
