package xyz.weechang.moreco.monitor.core.alarm;

import xyz.weechang.moreco.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义报警
 *
 * @author zhangwei
 * date 2018/12/21
 * time 13:34
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Alarm extends MonitorId {

    private static final long serialVersionUID = 3919385490212385609L;
    /**
     * 自定义报警点
     */
    private String key;

    /**
     * 自定义报警消息
     */
    private String msg;

    public Alarm(String groupKey, String appKey, String key, String msg) {
        super(groupKey, appKey);
        this.key = key;
        this.msg = msg;
    }
}
