package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 用户与角色对应关系
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
public class UserRoleDomain extends BaseDomain {
    private static final long serialVersionUID = -4753510506298775667L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
