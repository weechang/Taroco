package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserDeptDomain extends BaseDomain {
    private static final long serialVersionUID = 1717440330547599478L;

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 部门id
     */
    private Long deptId;
}
