package io.github.weechang.moreco.security.custom;

import io.github.weechang.moreco.security.config.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:53
 */
@Component
public class MorecoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.sendRedirect(SecurityProperties.loginSuccess);
    }
}
