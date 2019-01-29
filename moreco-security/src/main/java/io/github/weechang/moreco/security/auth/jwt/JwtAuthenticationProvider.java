package io.github.weechang.moreco.security.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.weechang.moreco.security.auth.common.MorecoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        DecodedJWT decodedJWT = null;
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            decodedJWT = jwtToken.getToken();
            // todo 验证Token否过期--时间、salt
        } else {
            Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
            String encodePwd = md5PasswordEncoder.encodePassword(password, userName);

            UserDetails userInfo = jwtUserDetailsService.loadUserByUsername(userName);

            if (!userInfo.getPassword().equals(encodePwd)) {
                throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
            }

            String token = jwtUserDetailsService.loginSuccess(userInfo);
            decodedJWT = JWT.decode(token);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        MorecoUserDetails morecoUserDetails = new MorecoUserDetails(userName, password);
        return new JwtAuthenticationToken(morecoUserDetails, decodedJWT, null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
