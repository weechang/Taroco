package xyz.weechang.taroco.core.query.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.io.Serializable;
import java.util.List;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/12 14:56.
 */
@NoRepositoryBean
public interface MongoBaseDao<T extends BaseEntry, ID extends Serializable> extends MongoRepository<T, ID> {

//    @Override
//    @Transactional(readOnly = true)
//    @Query("{'deleted':false}")
//    List<T> findAll();

//    @Override
//    @Transactional(readOnly = true)
//    @Query("{'deleted':false}")
//    Iterable<T> findAll(Iterable<ID> ids);

    @Override
    @Transactional(readOnly = true)
    @Query("{'deleted':false}")
    List<T> findAll(Sort sort);

//    @Override
//    @Transactional(readOnly = true)
//    @Query("{'deleted':false}")
//    Page<T> findAll(Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("{'_id':?0, 'deleted':false}")
    T findOne(ID id);

    @Override
    @Transactional(readOnly = true)
    @CountQuery("{'deleted':false}")
    long count();

    @Override
    @Transactional(readOnly = true)
    @ExistsQuery("{'_id':?0, 'deleted':false}")
    boolean exists(ID id);

    @Transactional
    default void logicDelete(ID id){
        T entity = findOne(id);
        logicDelete(entity);
    }

    @Transactional
    default void logicDelete(T entity) {
        entity.setDeleted(true);
        save(entity);
    }

    @Transactional
    default void logicDelete(Iterable<? extends T> entities) {
        entities.forEach(entity -> logicDelete(entity));
    }

    @Query("update #{#entityName} e set e.deleted = true ")
    @Transactional
    default void logicDeleteAll(){
        logicDelete(findAll());
    }
}
