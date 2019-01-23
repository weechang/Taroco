package io.github.weechang.moreco.monitor.sdk.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei
 * date 2018/11/15
 * time 14:41
 */
@ApiModel("监控配置项")
@NoArgsConstructor
@Data
public class MonitorConfig {

    @ApiModelProperty("项目编码")
    private String proCode;

    @ApiModelProperty("应用编码")
    private String appCode;

    @ApiModelProperty("上报路径")
    private String dataReportUrl;

    @ApiModelProperty("数据发送方式")
    private int dataSendType;

    private static MonitorConfig instance = new MonitorConfig();

    public static MonitorConfig getInstance() {
        return instance;
    }

}
