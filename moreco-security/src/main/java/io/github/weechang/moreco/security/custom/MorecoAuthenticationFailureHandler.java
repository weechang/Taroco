package io.github.weechang.moreco.security.custom;

import io.github.weechang.moreco.security.config.SecurityProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:52
 */
@Component
public class MorecoAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.sendRedirect(SecurityProperties.loginFailure);
    }
}
