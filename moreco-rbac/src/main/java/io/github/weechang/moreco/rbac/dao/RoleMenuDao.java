package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.model.domain.RbacRoleMenu;
import io.github.weechang.moreco.base.dao.JpaDao;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:11
 */
public interface RoleMenuDao extends JpaDao<RbacRoleMenu> {

    List<RbacRoleMenu> findAllByRoleId(Long id);
}
