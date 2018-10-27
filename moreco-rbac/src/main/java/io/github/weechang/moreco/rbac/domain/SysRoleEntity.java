package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * 角色
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleEntity extends BaseEntry {
    private static final long serialVersionUID = -6369262328565896728L;

    @Id
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;
}
