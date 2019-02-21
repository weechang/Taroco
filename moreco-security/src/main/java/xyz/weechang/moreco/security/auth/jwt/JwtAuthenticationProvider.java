package xyz.weechang.moreco.security.auth.jwt;

import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import xyz.weechang.moreco.security.auth.common.MorecoUserDetails;
import xyz.weechang.moreco.security.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
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
@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        DecodedJWT decodedJWT = null;
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            decodedJWT = jwtToken.getToken();

            // token 过期验证
            boolean isExpired = isExpired(decodedJWT.getExpiresAt());
            if (isExpired){
                throw new AccountExpiredException("token已过期");
            }
        } else {
            String encodePwd = SecureUtil.sha256(SecureUtil.sha256(username) + SecureUtil.sha256(password));
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

    /**
     * 判断token是否过期
     *
     * @param issueAt token过期时间
     * @return 是否过期
     */
    private boolean isExpired(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(securityProperties.getTokenExpiredTime()).isAfter(issueTime);
    }

}
