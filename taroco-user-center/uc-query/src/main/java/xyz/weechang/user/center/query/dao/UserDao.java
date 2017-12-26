package xyz.weechang.user.center.query.dao;

import xyz.weechang.taroco.core.dao.BaseDao;
import xyz.weechang.user.center.query.domain.UserEntry;

/**
 * 说明：用户查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:28.
 */
public interface UserDao extends BaseDao<UserEntry, String> {

    UserEntry findByUsername(String username);
}
