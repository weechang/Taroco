package io.github.moreco.monitor.core.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息主体构成
 *
 * @author zhangwei
 * date 2018/12/29
 * time 16:00
 */
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = -6678678601731414981L;

    /**
     * 类
     */
    private String clazz;

    /**
     * 消息内容
     */
    private String data;

    /**
     * 时间戳
     */
    private long timeStamp = System.currentTimeMillis();

    public Message(String clazz, String data) {
        this.clazz = clazz;
        this.data = data;
    }
}
