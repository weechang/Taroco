package xyz.weechang.taroco.base.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 抽象事件
 *
 * @author weechang
 */
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEvent implements Serializable {

    private static final long serialVersionUID = -8081085230074651557L;

    private String id;

    public String getId() {
        return id;
    }
}