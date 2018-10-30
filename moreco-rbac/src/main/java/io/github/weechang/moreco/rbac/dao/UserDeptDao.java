package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.model.domain.RbacRoleDept;
import io.github.weechang.moreco.rbac.model.domain.RbacUserDept;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:19
 */
public interface UserDeptDao extends JpaDao<RbacUserDept> {

    List<RbacUserDept> findAllByUserId(Long userId);
}
