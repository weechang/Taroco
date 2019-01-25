package io.github.weechang.moreco.security.base.config;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 安全验证参数
 *
 * @author zhangwei
 * date 2019/1/25
 * time 13:46
 */
@Data
@Component
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {

    /***参数前缀*/
    public static final String PREFIX = "moreco.security";

    /***安全插件 shrio || spring-security */
    public String security = "shiro";

    /***是否启用redis session key*/
    public static final String redisSessionKey = "redisSession";

    /***是否启用redis session*/
    public static final boolean redisSession = false;

    /***session 时效时间-单位秒*/
    public static final int invalidateTime = 0;

    /***session 验证失效时间-单位秒*/
    public static final int validationInterval = 0;

    /***session 的 cookie key*/
    public static final String sessionCookieKey = "moreco_tk";

    /*** 不需要授权的资源路径 */
    public List<String> nonePermissionRes = Lists.newArrayList();

}
