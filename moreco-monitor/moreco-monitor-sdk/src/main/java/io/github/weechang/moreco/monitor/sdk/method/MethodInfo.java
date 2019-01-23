package io.github.weechang.moreco.monitor.sdk.method;

import io.github.weechang.moreco.monitor.sdk.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 13:59
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class MethodInfo extends MonitorId {

    private final long beginTime = System.currentTimeMillis();
    private final long bt = System.nanoTime();
    private int count = 1;
    private long extime = 0L;
    private long et = 0L;
    private int success = 1;
    private String key;

    public MethodInfo(String projectCode, String appCode, String methodKey) {
        super(projectCode, appCode);
        this.key = methodKey;
    }

    public void completed() {
        this.extime = System.currentTimeMillis() - this.beginTime;
        this.et = System.nanoTime() - this.bt;
    }
}
