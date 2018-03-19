package xyz.weechang.taroco.core.query.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/22 13:57.
 */
@NoRepositoryBean
public interface MongoTreeDao<T extends BaseEntry, ID extends Serializable> extends MongoBaseDao<T, ID> {

    @Transactional(readOnly = true)
    @Query("{'parentId':null, 'deleted':false}")
    T findTree();
}
