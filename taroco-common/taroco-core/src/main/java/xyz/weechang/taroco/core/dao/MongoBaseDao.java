package xyz.weechang.taroco.core.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.model.BaseEntry;

import java.io.Serializable;
import java.util.List;

;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/12 14:56.
 */
@NoRepositoryBean
public interface MongoBaseDao<T extends BaseEntry, ID extends Serializable> extends MongoRepository<T, ID> {

    @Override
    @Transactional(readOnly = true)
//    @Query("select e from #{#entityName} e where e.deleted = false")
    @Query("{'deleted':false}")
    List<T> findAll();

//    @Override
//    @Transactional(readOnly = true)
//    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
//    Iterable<T> findAll(Iterable<ID> ids);
//
//    @Override
//    @Transactional(readOnly = true)
//    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
//    List<T> findAll(Sort sort);
//
//    @Override
//    @Transactional(readOnly = true)
//    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
//    Page<T> findAll(Pageable pageable);
//
//    @Override
//    @Transactional(readOnly = true)
//    @Query("select e from #{#entityName} e where e.id = ?1 and e.deleted = false")
//    T findOne(ID id);
//
//    @Override
//    @Transactional(readOnly = true)
//    @Query("select count(e) from #{#entityName} e where e.deleted = false")
//    long count();
//
//    @Override
//    @Transactional(readOnly = true)
//    default boolean exists(ID id) {
//        return findOne(id) != null;
//    }
//
//    @Query("update #{#entityName} e set e.deleted = true where e.id = ?1")
//    @Transactional
//    @Modifying
//    void logicDelete(ID id);
//
//    @Transactional
//    default void logicDelete(T entity) {
//        logicDelete((ID) entity.getId());
//    }
//
//    @Transactional
//    default void logicDelete(Iterable<? extends T> entities) {
//        entities.forEach(entity -> logicDelete((ID) entity.getId()));
//    }
//
//    @Query("update #{#entityName} e set e.deleted = true ")
//    @Transactional
//    @Modifying
//    void logicDeleteAll();
}
