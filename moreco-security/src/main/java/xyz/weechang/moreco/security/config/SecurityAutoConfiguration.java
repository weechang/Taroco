package xyz.weechang.moreco.security.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 23:58
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
//
//    @Autowired
//    private WebSecurityConfig webSecurityConfig;

    @Bean
    @ConditionalOnMissingBean
    public WebSecurityConfig webSecurityConfig(SecurityProperties securityProperties) {
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig();
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        configurableBeanFactory.registerSingleton("webSecurityConfig", webSecurityConfig);
        return webSecurityConfig;
    }
}
