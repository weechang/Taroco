package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class RoleDeptDomain extends BaseDomain {
    private static final long serialVersionUID = -8120106714249885343L;

    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;
}
