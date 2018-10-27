package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.domain.UserDeptDomain;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:19
 */
@NoRepositoryBean
public interface UserDeptDao extends JpaDao<UserDeptDomain> {
}
