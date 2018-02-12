package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.weechang.user.center.query.domain.MenuEntry;

/**
 * 说明：目录查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:24.
 */
public interface MenuDao extends MongoRepository<MenuEntry, String> {
}
