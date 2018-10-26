package io.github.weechang.moreco.sys.domain;

import io.github.weechang.weechang.moreco.query.domain.BaseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
