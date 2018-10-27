package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * 用户与角色对应关系
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRoleDomain extends BaseDomain {
    private static final long serialVersionUID = -4753510506298775667L;

    @Id
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
