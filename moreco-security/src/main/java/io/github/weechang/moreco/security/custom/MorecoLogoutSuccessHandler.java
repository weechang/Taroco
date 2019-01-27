package io.github.weechang.moreco.security.custom;

import io.github.weechang.moreco.security.config.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登出成功
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:54
 */
@Component
public class MorecoLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
        res.sendRedirect(SecurityProperties.loginSuccess);
    }
}
