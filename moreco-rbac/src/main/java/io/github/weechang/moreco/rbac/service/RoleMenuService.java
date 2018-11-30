package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.RoleMenu;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:59
 */
public interface RoleMenuService extends BaseService<RoleMenu> {

    /**
     * 根据角色id 查询对应关系
     * @param id 角色id
     * @return 对应关系
     */
    List<RoleMenu> findAllByRoleId(Long id);

    /**
     * 保存角色与目录关系
     *
     * @param roleId  角色id
     * @param menuIds 目录ids
     */
    void save(Long roleId, List<Long> menuIds);
}
