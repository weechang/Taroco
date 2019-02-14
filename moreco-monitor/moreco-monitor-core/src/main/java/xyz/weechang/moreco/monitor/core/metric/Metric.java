package xyz.weechang.moreco.monitor.core.metric;

import xyz.weechang.moreco.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * 自定义数据
 *
 * @author zhangwei
 * date 2018/12/29
 * time 14:30
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Metric extends MonitorId {
    private static final long serialVersionUID = 7198072544153444056L;

    /**
     * 自定义数据点
     */
    private String key;

    /**
     * 自定义数据
     */
    private HashMap<String, Object> msg = new HashMap<>();

    public Metric(String groupKey, String appKey, String key, HashMap<String, Object> msg) {
        super(groupKey, appKey);
        this.key = key;
        this.msg = msg;
    }

}
