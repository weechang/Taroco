package io.github.weechang.moreco.security.custom;

import io.github.weechang.moreco.security.config.SecurityProperties;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义403响应内容
 *
 * @author zhangwei
 * date 2019/1/26
 * time 21:09
 */
@Component
public class MorecoAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.sendRedirect(SecurityProperties.accessDenied);
    }
}
