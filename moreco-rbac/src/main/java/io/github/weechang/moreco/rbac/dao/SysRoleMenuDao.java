package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.domain.SysRoleMenuEntity;
import io.github.weechang.moreco.base.dao.JpaDao;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:11
 */
@NoRepositoryBean
public interface SysRoleMenuDao extends JpaDao<SysRoleMenuEntity, Long> {
}
