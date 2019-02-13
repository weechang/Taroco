package xyz.weechang.moreco.component.rbac.dao;

import xyz.weechang.moreco.component.rbac.model.domain.User;
import xyz.weechang.moreco.data.jpa.JpaBaseRepository;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:11
 */
public interface UserDao extends JpaBaseRepository<User> {

    User findFirstByUsername(String username);
}
