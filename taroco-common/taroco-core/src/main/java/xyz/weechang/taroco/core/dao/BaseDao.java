package xyz.weechang.taroco.core.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.model.BaseEntry;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：jpa新封装 提供逻辑删除
 *
 * @author zhangwei
 * @version 2017/11/20 14:03.
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntry, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.deleted = false")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
    Iterable<T> findAll(Iterable<ID> ids);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
    Iterable<T> findAll(Sort var1);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
    Page<T> findAll(Pageable var1);

    @Override
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.deleted = false")
    T findOne(ID id);

    @Override
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.deleted = false")
    long count();

    @Override
    @Transactional(readOnly = true)
    default boolean exists(ID id) {
        return findOne(id) != null;
    }

    @Query("update #{#entityName} e set e.deleted = true where e.id = ?1")
    @Transactional
    @Modifying
    void logicDelete(ID id);

    @Transactional
    default void logicDelete(T entity) {
        logicDelete((ID) entity.getId());
    }

    @Transactional
    default void logicDelete(Iterable<? extends T> entities) {
        entities.forEach(entity -> logicDelete((ID) entity.getId()));
    }

    @Query("update #{#entityName} e set e.deleted = true ")
    @Transactional
    @Modifying
    void logicDeleteAll();
}
