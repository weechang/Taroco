package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.base.dao.JpaDao;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface RoleDao extends JpaDao<Role> {

    Role findFirstByName(String name);
}
