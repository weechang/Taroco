package xyz.weechang.moreco.security.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import xyz.weechang.moreco.security.auth.common.*;
import xyz.weechang.moreco.security.auth.jwt.JwtAuthenticationProvider;
import xyz.weechang.moreco.security.auth.jwt.JwtAuthenticationSuccessHandler;
import xyz.weechang.moreco.security.auth.jwt.JwtAuthenticationTokenFilter;
import xyz.weechang.moreco.security.auth.jwt.JwtUserDetailsService;

/**
 * 自动配置
 *
 * @author zhangwei
 * date 2019/1/26
 * time 23:58
 */
@ComponentScan(value = {"xyz.weechang.moreco.security"})
@Configuration
public class SecurityAutoConfiguration implements BeanFactoryAware {

    @Bean
    @ConditionalOnMissingBean
    public MorecoAuthenticationEntryPoint morecoAuthenticationEntryPoint() {
        return new MorecoAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public MorecoAuthenticationFailureHandler morecoAuthenticationFailureHandler() {
        return new MorecoAuthenticationFailureHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public MorecoLogoutSuccessHandler morecoLogoutSuccessHandler() {
        return new MorecoLogoutSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public MorecoAccessDeniedHandler morecoAccessDeniedHandler() {
        return new MorecoAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtUserDetailsService jwtUserDetailsService() {
        return new JwtUserDetailsService();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
