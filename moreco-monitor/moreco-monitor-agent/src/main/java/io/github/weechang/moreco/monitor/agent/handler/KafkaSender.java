package io.github.weechang.moreco.monitor.agent.handler;

/**
 * 利用kafka 发送数据
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:59
 */
public class KafkaSender implements Sender {

    private static volatile KafkaSender sender;

    public static KafkaSender getInstance() {
        if (sender == null) {
            synchronized (KafkaSender.class) {
                if (sender == null) {
                    sender = new KafkaSender();
                }
            }
        }
        return sender;
    }

    @Override
    public boolean sendData(String msg) {
        return false;
    }
}
