package io.github.weechang.moreco.monitor.sdk.hearbeat;

import io.github.weechang.moreco.monitor.sdk.common.MonitorId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/17
 * time 18:05
 */
@ApiModel("心跳消息")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class HeartbeatInfo extends MonitorId {

    @ApiModelProperty
    private String version;
    @ApiModelProperty
    private Integer pid;
    @ApiModelProperty("启动时间")
    private Long startTime;
}
