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

/**
 * @author zhangwei
 * date 2018/11/29
 * time 11:40
 */
@ApiModel("方法")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_method")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Method extends BaseDomain {

    @ApiModelProperty("方法")
    private String method;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("监控频率")
    private Integer interval;

    @ApiModelProperty("报警开关")
    private Integer alarmStatus;
}
