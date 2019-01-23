package io.github.weechang.moreco.monitor.sdk.method;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 13:59
 */
@NoArgsConstructor
@Data
public class MethodCallInfo {

    private String className;
    private String methodName;
    private String spanId;
    private String parentId;
    private String traceId;
    private long beginTime;
    private long endTime;
    private long callTime;
    private int errCode;
    private String errorMsg;

}
