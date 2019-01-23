package io.github.weechang.moreco.monitor.sdk.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/12/17
 * time 18:02
 */
@ApiModel("监控id")
@Data
@NoArgsConstructor
public class MonitorId {

    @ApiModelProperty("项目编码")
    private String proCode;

    @ApiModelProperty("应用编码")
    private String appCode;

    @ApiModelProperty("host")
    private String host;

    @ApiModelProperty("实例编码")
    private String instanceCode;

    public MonitorId(String proCode, String appCode) {
        this.proCode = proCode;
        this.appCode = appCode;
//        this.host = MonitorUtil.getLocalIP();
//        this.instanceCode = MonitorUtil.getJvmInstanceCode();
    }
}
