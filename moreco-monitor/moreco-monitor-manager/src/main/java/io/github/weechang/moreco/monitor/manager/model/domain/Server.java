package io.github.weechang.moreco.monitor.manager.model.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 10:42
 */
@ApiModel("服务器")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_server")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Server extends BaseDomain {

    @ApiModelProperty("IP地址")
    private String ip;

    @ApiModelProperty("操作系统名称")
    private Integer OSType;

    @ApiModelProperty("操作系统版本")
    private String OSVersion;

    @ApiModelProperty("主机状态")
    private Integer serverStatus;

    @ApiModelProperty("核心数")
    private Integer cpuCore;

    @ApiModelProperty("CPU占用率")
    private Integer cpuRate;

    @ApiModelProperty("内存总数")
    private Integer memTotal;

    @ApiModelProperty("内存占用")
    private Integer memUsed;

    @ApiModelProperty("最后心跳时间")
    private Date lastHeartBeat;

    @ApiModelProperty("最后心跳时间")
    private Integer agentStatus;

    @ApiModelProperty("性能报警")
    private Integer performanceAlarm;

    @ApiModelProperty("存活报警")
    private Integer aliveAlarm;
}
