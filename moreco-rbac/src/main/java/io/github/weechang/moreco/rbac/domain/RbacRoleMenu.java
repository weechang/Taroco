package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 角色与菜单对应关系
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
@Where(clause = "yn = 1")
public class RbacRoleMenu extends BaseDomain {
    private static final long serialVersionUID = 17923510531021555L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
