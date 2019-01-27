package io.github.weechang.moreco.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 23:58
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityAutoConfiguration {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private WebSecurityConfig webSecurityConfig;


}
