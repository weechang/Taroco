package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.weechang.user.center.query.domain.RoleEntry;

/**
 * 说明：角色查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:27.
 */
public interface RoleDao extends MongoRepository<RoleEntry, String> {

    RoleEntry findByRoleName(String roleName);
}
