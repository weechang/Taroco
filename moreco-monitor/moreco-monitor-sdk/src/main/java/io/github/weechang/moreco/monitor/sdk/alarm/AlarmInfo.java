package io.github.weechang.moreco.monitor.sdk.alarm;

import io.github.weechang.moreco.monitor.sdk.common.MonitorId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 13:34
 */
@ApiModel("报警信息")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class AlarmInfo extends MonitorId {

    @ApiModelProperty("自定义报警点")
    private String key;

    @ApiModelProperty("自定义报警消息")
    private String message;

    public AlarmInfo(String proCode, String appCode, String alarmKey, String message) {
        super(proCode, appCode);
        this.key = alarmKey;
        this.message = message;
    }
}
