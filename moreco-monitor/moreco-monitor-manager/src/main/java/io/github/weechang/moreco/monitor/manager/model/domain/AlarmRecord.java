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
 * time 15:22
 */
@ApiModel("报警记录")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_alarm_record")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class AlarmRecord extends BaseDomain {

    @ApiModelProperty("规则编码")
    private String code;
}
