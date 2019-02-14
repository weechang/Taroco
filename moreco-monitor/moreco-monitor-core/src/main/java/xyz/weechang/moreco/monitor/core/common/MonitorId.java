package xyz.weechang.moreco.monitor.core.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 应用id
 *
 * @author zhangwei
 * date 2018/12/29
 * time 13:59
 */
@Data
@NoArgsConstructor
public class MonitorId implements Serializable {

    private static final long serialVersionUID = 8690126427362648489L;

    /**
     * 分组编码
     */
    private String groupKey;

    /**
     * 应用编码
     */
    private String appKey;

    /**
     * host
     */
    private String hostname;

    /**
     * 实例编码
     */
    private String instanceCode;

    public MonitorId(String groupKey, String appKey) {
        this.groupKey = groupKey;
        this.appKey = appKey;
    }
}
