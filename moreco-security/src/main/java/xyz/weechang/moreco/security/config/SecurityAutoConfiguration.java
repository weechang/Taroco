package xyz.weechang.moreco.security.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import xyz.weechang.moreco.security.auth.common.MorecoAccessDeniedHandler;
import xyz.weechang.moreco.security.auth.common.MorecoAuthenticationEntryPoint;
import xyz.weechang.moreco.security.auth.common.MorecoAuthenticationFailureHandler;
import xyz.weechang.moreco.security.auth.common.MorecoLogoutSuccessHandler;
import xyz.weechang.moreco.security.auth.jwt.*;

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
    public JwtUserDetailsService jwtUserDetailsService() {
        return new JwtUserDetailsService();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationRefreshFilter jwtAuthenticationRefreshFilter() {
        return new JwtAuthenticationRefreshFilter();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
