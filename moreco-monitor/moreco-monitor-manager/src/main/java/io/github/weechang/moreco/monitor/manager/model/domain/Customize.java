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
 * time 15:16
 */
@ApiModel("自定义")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_customize")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Customize extends BaseDomain {

    @ApiModelProperty("自定义名称")
    private String name;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("是否报警")
    private Integer alarmStatus;
}