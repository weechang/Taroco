package xyz.weechang.moreco.boot.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.weechang.moreco.component.rbac.dao.UserDao;
import xyz.weechang.moreco.component.rbac.model.domain.User;
import xyz.weechang.moreco.core.security.MorecoSecurityService;
import xyz.weechang.moreco.core.security.MorecoSecurityUser;

/**
 * 自己实现Security
 *
 * @author zhangwei
 * date 2019/2/13
 * time 15:04
 */
@Service
public class MyMorecoSecurityService implements MorecoSecurityService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isUrlPermissionByName(String username, String url) {
        return true;
    }

    @Override
    public MorecoSecurityUser findFirstByUsername(String username) {
        MorecoSecurityUser securityUser = new MorecoSecurityUser();
        User user = userDao.findFirstByUsername(username);
        if (user != null) {
            securityUser.setUsername(user.getUsername());
            securityUser.setPassword(user.getPassword());
        }
        return securityUser;
    }
}
