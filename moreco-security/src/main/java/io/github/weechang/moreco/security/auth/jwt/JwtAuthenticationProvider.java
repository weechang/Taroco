package io.github.weechang.moreco.security.auth.jwt;

import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.weechang.moreco.security.auth.common.MorecoUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

    private static final int tokenRefreshInterval = 300;  //刷新间隔5分钟

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        DecodedJWT decodedJWT = null;
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            decodedJWT = jwtToken.getToken();
            // todo token 是否正确
            boolean shouldRefresh = shouldTokenRefresh(decodedJWT.getIssuedAt());
            if (shouldRefresh) {
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
                String authToken = jwtUserDetailsService.loginSuccess(userDetails);
                decodedJWT = JWT.decode(authToken);
            }
        } else {
            String encodePwd = new String(DigestUtil.sha256(username, password));
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (!userDetails.getPassword().equals(encodePwd)) {
                throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
            }
            String token = jwtUserDetailsService.loginSuccess(userDetails);
            decodedJWT = JWT.decode(token);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MorecoUserDetails morecoUserDetails = new MorecoUserDetails(username, password);
        return new JwtAuthenticationToken(morecoUserDetails, decodedJWT, null);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    protected boolean shouldTokenRefresh(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }

}
