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
 * time 15:50
 */
@ApiModel("人员")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_member")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Member extends BaseDomain {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("电话")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;
}
