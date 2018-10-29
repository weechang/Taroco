package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.domain.RbacMenu;
import io.github.weechang.moreco.base.dao.JpaDao;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface MenuDao extends JpaDao<RbacMenu> {

    List<RbacMenu> findAllByParentId(Long parentId);

    List<RbacMenu> findAllByParentIdAndIdContains(Long parentId, List<Long> ids);

    RbacMenu findFirstByNameAndParentId(String name, Long parentId);
}
