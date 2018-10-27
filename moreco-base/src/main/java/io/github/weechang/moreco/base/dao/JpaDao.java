package io.github.weechang.moreco.base.dao;

import io.github.weechang.moreco.base.domain.BaseDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * JPA dao
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:01
 */
@NoRepositoryBean
public interface JpaDao<T extends BaseDomain> extends BaseDao<T> {

    @Override
    @Query("update #{#entityName} e set e.deleted = true ")
    @Transactional
    void logicDeleteAll();
}
