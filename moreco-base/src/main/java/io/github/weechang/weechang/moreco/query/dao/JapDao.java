package io.github.weechang.weechang.moreco.query.dao;

import io.github.weechang.weechang.moreco.query.domain.BaseEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * JPA dao
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:01
 */
@NoRepositoryBean
public interface JapDao<T extends BaseEntry, ID extends Serializable> extends BaseDao<T, ID> {

    @Override
    @Query("update #{#entityName} e set e.deleted = true ")
    @Transactional
    void logicDeleteAll();
}
