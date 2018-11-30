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
 * time 11:03
 */
@ApiModel("应用")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_app")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class App extends BaseDomain {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("应用类型")
    private Integer appType;

    @ApiModelProperty("性能报警")
    private Integer performanceAlarm;

    @ApiModelProperty("心跳报警")
    private Integer heartBeatAlarm;

    @ApiModelProperty("报警间隔")
    private Integer alarmInterval;
}
