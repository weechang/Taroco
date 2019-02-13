package xyz.weechang.moreco.component.rbac.dao;

import xyz.weechang.moreco.component.rbac.model.domain.Role;
import xyz.weechang.moreco.data.jpa.JpaBaseRepository;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface RoleDao extends JpaBaseRepository<Role> {

    Role findFirstByName(String name);
}
