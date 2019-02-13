package xyz.weechang.moreco.security.auth.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT 注销
 *
 * @author zhangwei
 * date 2019/1/28
 * time 23:22
 */
@Slf4j
@Component
public class JwtLogoutHandler implements LogoutHandler {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public void logout(HttpServletRequest req, HttpServletResponse res, Authentication auth) {
        clearToken(auth);
    }

    protected void clearToken(Authentication authentication) {
        if (authentication == null) {
            return;
        }
        UserDetails user = (UserDetails) authentication.getPrincipal();
        if (user != null && user.getUsername() != null) {
            jwtUserDetailsService.deleteUserLoginInfo(user.getUsername());
        }
    }
}
