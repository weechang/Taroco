package io.github.weechang.moreco.sys.dao;

import io.github.weechang.moreco.sys.domain.SysUserDeptEntity;
import io.github.weechang.weechang.moreco.base.dao.JpaDao;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:19
 */
@NoRepositoryBean
public interface SysUserDeptDao extends JpaDao<SysUserDeptEntity, Long> {
}
