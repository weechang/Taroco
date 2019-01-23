package io.github.weechang.moreco.monitor.sdk.jvm;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 13:49
 */
@ApiModel("jvm gc 信息")
@NoArgsConstructor
@Data
public class BufferPoolInfo {

    private Long totalCapacity;
    private Long memoryUsed;
    private Long count;
    private String poolName;
}
