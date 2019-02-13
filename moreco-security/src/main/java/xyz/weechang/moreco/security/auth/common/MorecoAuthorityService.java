package xyz.weechang.moreco.security.auth.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.weechang.moreco.core.security.MorecoSecurityService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限验证
 *
 * @author zhangwei
 * date 2019/1/27
 * time 16:39
 */
@Slf4j
@Component("morecoAuthorityservice")
public class MorecoAuthorityService {

    @Autowired
    private MorecoSecurityService morecoSecurityService;

    public boolean hasPermission(HttpServletRequest req, Authentication authentication) {
        boolean hasPermission = false;
        String username = (String) authentication.getPrincipal();
        if (username != null){
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
            if (!hasPermission){
                morecoSecurityService.isUrlPermissionByName(username, req.getRequestURI());
            }
        }
        return hasPermission;
    }
}
