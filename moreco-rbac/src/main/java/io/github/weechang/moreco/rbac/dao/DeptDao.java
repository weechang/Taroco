package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.model.domain.Dept;
import io.github.weechang.moreco.base.dao.JpaDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:09
 */
public interface DeptDao extends JpaDao<Dept> {

    List<Dept> findAllByParentId(Long parentId);

    Page<Dept> queryAllByParentId(Long parentId, Pageable pageable);

    Dept findFirstByNameAndParentId(String name, Long parentId);
}
