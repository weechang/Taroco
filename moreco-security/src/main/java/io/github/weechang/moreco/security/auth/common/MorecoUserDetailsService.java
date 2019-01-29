package io.github.weechang.moreco.security.auth.common;

import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.rbac.dao.UserDao;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.security.error.SecurityError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsService 实现
 *
 * @author zhangwei
 * date 2019/1/26
 * time 20:49
 */
public class MorecoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findFirstByUsername(username);
        if (user == null) {
            throw new AppException(SecurityError.USER_NOT_FOUNT);
        }
        return new MorecoUserDetails(user.getUsername(), user.getPassword());
    }
}
