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
 * date 2018/11/27
 * time 14:14
 */
@ApiModel("项目")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_project")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Project extends BaseDomain {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("编码")
    private String code;
}
