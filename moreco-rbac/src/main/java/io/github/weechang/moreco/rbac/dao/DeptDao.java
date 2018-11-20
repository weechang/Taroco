package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:09
 */
public interface DeptDao extends JpaDao<RbacDept> {

    List<RbacDept> findAllByParentId(Long parentId);

    Page<RbacDept> queryAllByParentId(Long parentId, Pageable pageable);

    RbacDept findFirstByNameAndParentId(String name, Long parentId);
}
