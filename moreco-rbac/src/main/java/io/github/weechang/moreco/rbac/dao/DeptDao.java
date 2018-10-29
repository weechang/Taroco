package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.domain.RbacDept;
import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.domain.RbacMenu;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:09
 */
public interface DeptDao extends JpaDao<RbacDept> {

    List<RbacDept> queryAllByParentId(Long parentId);

    RbacDept findFirstByNameAndParentId(String name, Long parentId);
}
