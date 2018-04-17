package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;
import xyz.weechang.user.center.query.domain.RoleEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：角色查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:27.
 */
@Transactional(readOnly = true)
public interface RoleDao extends MongoBaseDao<RoleEntry, String> {

    @Query(value = "{'name':?0, 'deleted':false}" ,count = true)
    int countByName(String name);

    default List<RoleEntry> findByIds(List<String> ids) {
        List<RoleEntry> roles = new ArrayList<RoleEntry>();
        for (String id : ids){
            roles.add(findOne(id));
        }
        return roles;
    }

}
