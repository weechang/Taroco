package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.domain.UserRoleDomain;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:11
 */
@NoRepositoryBean
public interface UserRoleDao extends JpaDao<UserRoleDomain> {

    List<UserRoleDomain> findAllByUserId(Long id);
}
