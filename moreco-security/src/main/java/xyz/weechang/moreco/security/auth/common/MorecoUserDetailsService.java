package xyz.weechang.moreco.security.auth.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.weechang.moreco.core.exception.AppException;
import xyz.weechang.moreco.core.security.MorecoSecurityService;
import xyz.weechang.moreco.core.security.MorecoSecurityUser;
import xyz.weechang.moreco.security.error.SecurityError;

/**
 * UserDetailsService 实现
 *
 * @author zhangwei
 * date 2019/1/26
 * time 20:49
 */
@Component
public class MorecoUserDetailsService implements UserDetailsService {

    @Autowired
    private MorecoSecurityService morecoSecurityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MorecoSecurityUser user = morecoSecurityService.findFirstByUsername(username);
        if (user == null) {
            throw new AppException(SecurityError.USER_NOT_FOUNT);
        }
        return new MorecoUserDetails(user.getUsername(), user.getPassword());
    }
}
