package io.github.weechang.moreco.security.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 自定义登录认证
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:56
 */
@Component
public class MorecoAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MorecoUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal(); // 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials(); // 这个是表单中输入的密码；

        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String encodePwd = md5PasswordEncoder.encodePassword(password, userName);

        UserDetails userInfo = userDetailsService.loadUserByUsername(userName);

        if (!userInfo.getPassword().equals(encodePwd)) {
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }

        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
