package io.github.weechang.moreco.monitor.sdk.method;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 14:05
 */
@NoArgsConstructor
@Data
public class MethodInvocationData {

    private String methodName;
    private Long beginDate;
    private List<Long> usedTimes = new ArrayList<>();
    private MethodInvocationData parent;
    private List<MethodInvocationData> childList = new ArrayList<>();
}
