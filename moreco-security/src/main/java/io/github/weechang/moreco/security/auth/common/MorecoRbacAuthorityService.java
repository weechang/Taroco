package io.github.weechang.moreco.security.auth.common;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * RBAC 权限验证
 *
 * @author zhangwei
 * date 2019/1/27
 * time 16:39
 */
@Component("rbacAuthorityservice")
public class MorecoRbacAuthorityService {

    public boolean hasPermission(HttpServletRequest req, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        boolean hasPermission = false;
        // 公共资源
        Set<String> noAuthUrls = new HashSet<>();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String url : noAuthUrls) {
            if (antPathMatcher.match(url, req.getRequestURI())) {
                hasPermission = true;
                break;
            }
        }
        // 登录授权资源
        hasPermission = true;
        return hasPermission;
    }
}
