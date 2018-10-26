package io.github.weechang.weechang.moreco.base.dao;

import io.github.weechang.weechang.moreco.base.domain.BaseEntry;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Dao类基本方法
 *
 * @author zhangwei
 * date 2018/10/26
 * time 16:21
 */
@NoRepositoryBean
interface BaseDao<T extends BaseEntry, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    @Transactional
    default void logicDelete(ID id){
        T entity = findOne(id);
        logicDelete(entity);
    }

    @Transactional
    default void logicDelete(T entity) {
        entity.setDeleted(1);
        save(entity);
    }

    @Transactional
    default void logicDelete(Iterable<? extends T> entities) {
        entities.forEach(entity -> logicDelete(entity));
    }

    @Query("update #{#entityName} e set e.deleted = true ")
    @Transactional
    void logicDeleteAll();

}
