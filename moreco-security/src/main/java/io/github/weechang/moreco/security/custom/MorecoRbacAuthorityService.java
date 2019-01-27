package io.github.weechang.moreco.security.custom;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
@Component("rbacauthorityservice")
public class MorecoRbacAuthorityService {

    public boolean hasPermission(HttpServletRequest req, Authentication authentication) {
        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;
        if (userInfo instanceof UserDetails) {
            String username = ((UserDetails) userInfo).getUsername();
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

            return hasPermission;
        } else {
            return false;
        }
    }
}
