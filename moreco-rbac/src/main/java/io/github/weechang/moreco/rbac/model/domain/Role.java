package io.github.weechang.moreco.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.weechang.moreco.base.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 17:55
 */
@ApiModel("角色")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_rbac_role", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Role extends BaseDomain {
    private static final long serialVersionUID = -6369262328565896728L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("目录")
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "moreco_rbac_role_menu",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
    private List<Menu> menus;

    @ApiModelProperty("目录")
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "moreco_rbac_user_role",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> users;
}
