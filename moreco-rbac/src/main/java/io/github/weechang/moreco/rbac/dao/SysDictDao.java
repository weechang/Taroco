package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.rbac.domain.SysDictEntity;
import io.github.weechang.moreco.base.dao.JpaDao;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:09
 */
@NoRepositoryBean
public interface SysDictDao extends JpaDao<SysDictEntity, Long> {
}
