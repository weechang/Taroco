package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.model.domain.RbacRoleDept;
import io.github.weechang.moreco.base.dao.JpaDao;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface RoleDeptDao extends JpaDao<RbacRoleDept> {

    List<RbacRoleDept> findAllByRoleId(Long roleId);
}
