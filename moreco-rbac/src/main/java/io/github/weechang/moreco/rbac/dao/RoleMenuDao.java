package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.domain.RoleMenuDomain;
import io.github.weechang.moreco.base.dao.JpaDao;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:11
 */
@NoRepositoryBean
public interface RoleMenuDao extends JpaDao<RoleMenuDomain> {

    List<RoleMenuDomain> findAllByRoleId(Long id);
}
