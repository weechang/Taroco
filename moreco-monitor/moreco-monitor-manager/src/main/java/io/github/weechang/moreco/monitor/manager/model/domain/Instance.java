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
 * time 11:10
 */
@ApiModel("实例")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_instance")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Instance extends BaseDomain {

    @ApiModelProperty("主机地址")
    private String ip;

    @ApiModelProperty("端口")
    private String port;

    @ApiModelProperty("进程编号")
    private Integer pid;

    @ApiModelProperty("启动时间")
    private Date startDate;

    @ApiModelProperty("最后心跳时间")
    private Date lastHeartBeat;

    @ApiModelProperty("心跳状态")
    private Integer heartBeatStatus;

    @ApiModelProperty("CPU占用率")
    private Float cpuRate;

    @ApiModelProperty("堆内存占用率")
    private Float stackMemRate;
}
