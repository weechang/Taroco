package io.github.weechang.moreco.monitor.sdk.jvm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/3
 * time 14:59
 */
@ApiModel("jvm gc 信息")
@NoArgsConstructor
@Data
public class JvmGcInfo {

    @ApiModelProperty("GC名称")
    private String gcName;

    @ApiModelProperty("GC次数")
    private long gcTotalCount;

    @ApiModelProperty("GC占用时间")
    private long gcTotalTime;
}
