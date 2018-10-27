package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 角色与部门对应关系
 * <p>
 * 用于数据权限管理
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
public class RoleDeptDomain extends BaseDomain {
    private static final long serialVersionUID = -8120106714249885343L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;
}
