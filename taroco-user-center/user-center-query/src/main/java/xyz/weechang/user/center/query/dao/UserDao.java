package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.weechang.user.center.query.domain.UserEntry;

/**
 * 说明：用户查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:28.
 */
public interface UserDao extends MongoRepository<UserEntry, String> {

    UserEntry findByUsername(String username);
}
