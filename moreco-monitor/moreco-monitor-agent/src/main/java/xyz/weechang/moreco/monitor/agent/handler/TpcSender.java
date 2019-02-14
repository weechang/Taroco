package xyz.weechang.moreco.monitor.agent.handler;

import lombok.extern.slf4j.Slf4j;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;
import xyz.weechang.moreco.monitor.core.common.Const;
import xyz.weechang.moreco.monitor.core.common.McmPacket;
import xyz.weechang.moreco.monitor.core.config.Agent;

import java.io.IOException;

/**
 * Tcp 方式发送消息
 *
 * @author zhangwei
 * date 2018/12/29
 * time 15:58
 */
@Slf4j
public class TpcSender implements Sender {

    private static volatile TpcSender sender;

    public static TpcSender getInstance() {
        if (sender == null) {
            synchronized (TpcSender.class) {
                if (sender == null) {
                    sender = new TpcSender();
                    sender.init();
                }
            }
        }
        return sender;
    }

    private static TcpSendClient tcpSendClient = new TcpSendClient();
    private static ClientAioListener aioListener = null;
    private static ReconnConf reconnConf = new ReconnConf(5000L);
    private static ClientGroupContext clientGroupContext = new ClientGroupContext(tcpSendClient, aioListener, reconnConf);
    private static TioClient tioClient = null;
    private static ClientChannelContext clientChannelContext = null;

    @Override
    public boolean sendData(String msg) {
        boolean send = true;
        if (msg != null) {
            try {
                Node node = getNode();
                clientChannelContext = tioClient.connect(node);
                McmPacket packet = new McmPacket();
                packet.setBody(msg.getBytes(McmPacket.CHARSET));
                Tio.send(clientChannelContext, packet);
            } catch (Exception e) {
                send = false;
                log.error("发送消息失败", e);
            }
        }
        return send;
    }

    private void init() {
        try {
            clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
            tioClient = new TioClient(clientGroupContext);
        } catch (IOException e) {
            log.error("初始化错误", e);
        }
    }

    /**
     * 获取接收消息的服务器
     *
     * @return 服务器配置
     */
    private Node getNode() {
        int nodesSize = Agent.getInstance().nodes.size();
        int random = (int) (1 + Math.random() * nodesSize) - 1;
        Node node = new Node(Agent.getInstance().nodes.get(random).host, Agent.getInstance().nodes.get(random).port);
        return node;
    }

}
