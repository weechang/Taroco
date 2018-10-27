package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.domain.DeptDomain;
import io.github.weechang.moreco.base.dao.JpaDao;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:09
 */
@NoRepositoryBean
public interface DeptDao extends JpaDao<DeptDomain> {

    List<DeptDomain> queryAllByParentId(Long parentId);
}
