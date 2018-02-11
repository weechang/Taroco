package xyz.weechang.taroco.core.event;


import java.io.Serializable;

/**
 * 抽象事件
 *
 * @author weechang
 */
public abstract class AbstractEvent implements Serializable {

    private static final long serialVersionUID = -8081085230074651557L;

    private String id;

    public AbstractEvent() {

    }

    public AbstractEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}