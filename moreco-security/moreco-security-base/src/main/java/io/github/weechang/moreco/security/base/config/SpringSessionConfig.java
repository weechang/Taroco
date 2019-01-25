package io.github.weechang.moreco.security.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 分布式session
 *
 * @author zhangwei
 * date 2019/1/25
 * time 13:16
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = SecurityProperties.invalidateTime)
@ConditionalOnProperty(prefix = SecurityProperties.PREFIX, name = SecurityProperties.redisSessionKey, havingValue = "true")
public class SpringSessionConfig {

}
