package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;
import xyz.weechang.user.center.query.domain.UserEntry;

/**
 * 说明：用户查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:28.
 */
public interface UserDao extends MongoBaseDao<UserEntry, String> {

    @Transactional(readOnly = true)
    @Query(value = "{'username':?0, 'deleted':false}" ,count = true)
    int countByUsername(String username);

}
