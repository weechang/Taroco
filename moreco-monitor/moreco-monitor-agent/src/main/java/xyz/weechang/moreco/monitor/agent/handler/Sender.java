package xyz.weechang.moreco.monitor.agent.handler;

/**
 * 消息发送器
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:58
 */
public interface Sender {

    /**
     * 发送数据
     *
     * @param msg 发送的消息
     * @return 发送结果
     */
    boolean sendData(String msg);
}
