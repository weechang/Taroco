package io.github.weechang.moreco.rbac.model.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:55
 */
@ApiModel("角色")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
@Where(clause = "yn = 1")
public class RbacRole extends BaseDomain {
    private static final long serialVersionUID = -6369262328565896728L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @Transient
    private List<Long> deptIdList;

    @Transient
    private List<Long> menuIdList;
}
