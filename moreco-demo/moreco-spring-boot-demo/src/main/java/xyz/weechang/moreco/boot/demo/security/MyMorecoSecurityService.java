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

    /**
     * 根据用户名和请求路径，判断是否有访问权限
     *
     * @param username 用户名
     * @param url      请求路径
     * @return 是否有访问权限
     */
    @Override
    public boolean isUrlPermissionByName(String username, String url) {
        return true;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public MorecoSecurityUser findFirstByUsername(String username) {
        MorecoSecurityUser securityUser = new MorecoSecurityUser();
        // 此处应根据自身业务情况进行
        User user = userDao.findFirstByUsername(username);
        if (user != null) {
            securityUser.setUsername(user.getUsername());
            securityUser.setPassword(user.getPassword());
        }
        return securityUser;
    }
}
