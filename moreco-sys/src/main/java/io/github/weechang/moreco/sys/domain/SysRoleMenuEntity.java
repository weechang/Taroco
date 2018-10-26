package io.github.weechang.moreco.sys.domain;

import io.github.weechang.weechang.moreco.query.domain.BaseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * 角色与菜单对应关系
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleMenuEntity extends BaseEntry {
    private static final long serialVersionUID = 17923510531021555L;

    @Id
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
