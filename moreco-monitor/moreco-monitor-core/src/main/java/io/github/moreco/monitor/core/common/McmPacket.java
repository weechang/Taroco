package io.github.moreco.monitor.core.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tio.core.intf.Packet;

/**
 * 消息Packet
 *
 * @author zhangwei
 * date 2018/12/30
 * time 13:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class McmPacket extends Packet {
    private static final long serialVersionUID = -3322188526422929694L;

    public static final int HEADER_LENGTH = 4;//消息头的长度
    public static final String CHARSET = "utf-8";
    private byte[] body;
}
