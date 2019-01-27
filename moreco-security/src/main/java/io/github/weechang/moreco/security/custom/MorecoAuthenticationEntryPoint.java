package io.github.weechang.moreco.security.custom;

import io.github.weechang.moreco.security.config.SecurityProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义未登录
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:51
 */
@Component
public class MorecoAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.sendRedirect(SecurityProperties.unLoginPath);
    }
}
