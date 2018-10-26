package io.github.weechang.moreco.sys.dao;

import io.github.weechang.moreco.sys.domain.SysConfigEntity;
import io.github.weechang.weechang.moreco.query.dao.JpaDao;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
@NoRepositoryBean
public interface SysRoleDeptDao extends JpaDao<SysConfigEntity, Long> {
}
