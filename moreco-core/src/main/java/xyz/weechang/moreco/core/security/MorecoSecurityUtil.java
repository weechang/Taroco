package xyz.weechang.moreco.core.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全类
 *
 * @author zhangwei
 * date 2019/12/23
 * time 15:07
 */
public class MorecoSecurityUtil {

    /*** JWT用户ID key */
    public static final String USER_ID = "userId";

    private static Claim getClaim(String key) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        MorecoAuthenticationToken morecoToken = (MorecoAuthenticationToken) authentication;
        DecodedJWT decodedJWT = morecoToken.getToken();
        return decodedJWT.getClaim(key);
    }

    public static Long getUserId() {
        Claim userIdClaim = getClaim(USER_ID);
        if (userIdClaim == null) {
            return -99L;
        }
        return userIdClaim.asLong();
    }
}
