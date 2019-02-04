package io.github.moreco.monitor.core.method;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 方法调用
 *
 * @author zhangwei
 * date 2018/12/21
 * time 13:59
 */
@NoArgsConstructor
@Data
public class MethodCall {

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
