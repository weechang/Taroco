package xyz.weechang.user.center.query.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.weechang.taroco.base.dao.BaseDao;
import xyz.weechang.user.center.query.domain.RoleEntry;

/**
 * 说明：角色查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:27.
 */
@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RoleDao extends BaseDao<RoleEntry, String> {

    RoleEntry findByRoleName(String roleName);
}
