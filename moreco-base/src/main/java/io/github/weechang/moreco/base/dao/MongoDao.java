package io.github.weechang.moreco.base.dao;

import io.github.weechang.moreco.base.domain.BaseDomain;
import io.github.weechang.moreco.base.domain.enums.YnEnums;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
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
public interface MongoDao<T extends BaseDomain> extends BaseDao<T>, MongoRepository<T, Serializable> {

//    @Override
//    @Transactional(readOnly = true)
//    @Query("{'yn':0}")
//    List<T> findAll(Sort sort);
//
//    @Override
//    @Transactional(readOnly = true)
//    @Query("{'_id':?0, 'yn':0}")
//    T findOne(Serializable id);
//
//    @Override
//    @Transactional(readOnly = true)
//    @CountQuery("{'yn':0}")
//    long count();
//
////    @Override
////    @Transactional(readOnly = true)
////    @ExistsQuery("{'_id':?0, 'yn':0}")
////    boolean exists(Serializable id);
//
//    @Transactional
//    default void logicDelete(Serializable id){
//        T entity = findOne(id);
//        logicDelete(entity);
//    }
//
//    @Transactional
//    default void logicDelete(T entity) {
//        entity.setYn(YnEnums.N.getKey());
//        save(entity);
//    }
//
//    @Transactional
//    default void logicDelete(Iterable<? extends T> entities) {
//        entities.forEach(entity -> logicDelete(entity));
//    }
//
////    @Query("update #{#entityName} e set e.yn = 0 ")
////    @Transactional
////    void logicDeleteAll();
}
