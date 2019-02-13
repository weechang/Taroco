package xyz.weechang.moreco.cloud.gateway.filter.pre;

import cn.hutool.core.collection.CollectionUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

/**
 * 将认证用户的相关信息 放入header中, 后端服务可以直接读取使用
 * 在RateLimitPreFilter 之前执行，不然有空指针问题
 *
 * @author liuht
 * @date 2017/11/20
 */
@Component
public class UserInfoHeaderFilter extends ZuulFilter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.set("startTime", System.currentTimeMillis());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.addZuulRequestHeader(SecurityConstants.USER_HEADER, authentication.getName());
            requestContext.addZuulRequestHeader(SecurityConstants.ROLE_HEADER, CollectionUtil.join(authentication.getAuthorities(), ","));
            String tokenValue = extractToken(request);
            if (!StringUtils.isEmpty(tokenValue)) {
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
                if (accessToken != null && !CollectionUtils.isEmpty(accessToken.getAdditionalInformation())) {
                    requestContext.addZuulRequestHeader(CommonConstant.HEADER_LABEL, accessToken.getAdditionalInformation
                            ().get(CommonConstant.HEADER_LABEL) + "");
                }
            }
        }
        return null;
    }

    /**
     * 获取请求Bearer Token
     * @param request request对象
     * @return tokenValue
     */
    private String extractToken(HttpServletRequest request) {
        String token = extractHeaderToken(request);
        if (token == null) {
            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
        }
        return token;
    }

    /**
     * 请求头中获取Bearer Token
     *
     * @param request The request.
     * @return The token, or null if no OAuth authorization header was supplied.
     */
    private String extractHeaderToken(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(CommonConstant.TOKEN_HEADER);
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }

        return null;
    }
}
