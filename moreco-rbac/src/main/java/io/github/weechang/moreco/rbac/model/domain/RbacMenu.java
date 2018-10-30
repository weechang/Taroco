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
 * 菜单
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:55
 */
@ApiModel("目录")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
@Where(clause = "yn = 1")
public class RbacMenu extends BaseDomain {
    private static final long serialVersionUID = 5051501706109694638L;

    @ApiModelProperty("父菜单ID，一级菜单为0")
    private Long parentId;

    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("菜单URL")
    private String url;

    @ApiModelProperty("授权(多个用逗号分隔，如：/rbac/user/list,/rbac/user/edit)")
    private String perms;

    @ApiModelProperty("类型     0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("子目录")
    @Transient
    private List<RbacMenu> children;
}
