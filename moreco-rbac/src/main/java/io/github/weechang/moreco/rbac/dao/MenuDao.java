package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import io.github.weechang.moreco.base.dao.JpaDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface MenuDao extends JpaDao<RbacMenu> {

    List<RbacMenu> findAllByParentId(Long parentId);

    Page<RbacMenu> findAllByParentId(Long parentId, Pageable pageable);

    RbacMenu findFirstByNameAndParentId(String name, Long parentId);
}
