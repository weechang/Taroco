package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.domain.RbacRoleMenu;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:59
 */
public interface RoleMenuService extends BaseService<RbacRoleMenu> {

    /**
     * 根据角色id 查询对应关系
     * @param id 角色id
     * @return 对应关系
     */
    List<RbacRoleMenu> findAllByRoleId(Long id);
}
