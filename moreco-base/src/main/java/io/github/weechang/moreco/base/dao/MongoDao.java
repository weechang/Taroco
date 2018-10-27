package io.github.weechang.moreco.base.dao;

import io.github.weechang.moreco.base.domain.BaseDomain;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Mongo Dao
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:02
 */
public interface MongoDao<T extends BaseDomain> extends BaseDao<T> {

    @Override
    @Transactional(readOnly = true)
    @Query("{'deleted':false}")
    List<T> findAll(Sort sort);

    @Override
    @Transactional(readOnly = true)
    @Query("{'_id':?0, 'deleted':false}")
    T findOne(Serializable id);

    @Override
    @Transactional(readOnly = true)
    @CountQuery("{'deleted':false}")
    long count();

    @Override
    @Transactional(readOnly = true)
    @ExistsQuery("{'_id':?0, 'deleted':false}")
    boolean exists(Serializable id);

    @Override
    @Query("update #{#entityName} e set e.deleted = true ")
    @Transactional
    void logicDeleteAll();
}
