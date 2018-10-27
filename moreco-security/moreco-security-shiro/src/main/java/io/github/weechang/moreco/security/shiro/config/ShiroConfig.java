//package io.github.weechang.moreco.security.shiro.config;
//
//import io.github.weechang.moreco.security.shiro.dao.MysqlShiroSessionDAO;
//import io.github.weechang.moreco.security.shiro.dao.RedisShiroSessionDAO;
//import io.github.weechang.moreco.security.shiro.realm.UserRealm;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * Shiro的配置文件
// *
// */
//@Configuration
//public class ShiroConfig {
//
//    @Bean("sessionManager")
//    public SessionManager sessionManager(RedisShiroSessionDAO redisShiroSessionDAO,
//                                         MysqlShiroSessionDAO mysqlShiroSessionDAO,
//                                         @Value("${renren.redis.open}") boolean redisOpen,
//                                         @Value("${renren.shiro.redis}") boolean shiroRedis,
//                                         @Value("${renren.shiro.mysql}") boolean shiroMysql){
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
//        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//
//
//        //如果开启redis缓存且renren.shiro.redis=true，则shiro session存到redis里
//        if(redisOpen && shiroRedis){
//            sessionManager.setSessionDAO(redisShiroSessionDAO);
//        } else if(shiroMysql){
//            sessionManager.setSessionDAO(mysqlShiroSessionDAO);
//        }
//        return sessionManager;
//    }
//
//    @Bean("securityManager")
//    public SecurityManager securityManager(UserRealm userRealm, SessionManager sessionManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(userRealm);
//        securityManager.setSessionManager(sessionManager);
//
//        return securityManager;
//    }
//
//
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//        shiroFilter.setLoginUrl("/login.html");
//        shiroFilter.setUnauthorizedUrl("/");
//
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/swagger/**", "anon");
//        filterMap.put("/v2/api-docs", "anon");
//        filterMap.put("/swagger-ui.html", "anon");
//        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/swagger-resources/**", "anon");
//
//        filterMap.put("/statics/**", "anon");
//        filterMap.put("/login.html", "anon");
//        filterMap.put("/rbac/login", "anon");
//        filterMap.put("/favicon.ico", "anon");
//        filterMap.put("/captcha.jpg", "anon");
//
//        filterMap.put("/api/**", "anon");
//
//        filterMap.put("/**", "authc");
//        shiroFilter.setFilterChainDefinitionMap(filterMap);
//
//        return shiroFilter;
//    }
//
//    @Bean("lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
//        proxyCreator.setProxyTargetClass(true);
//        return proxyCreator;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//}
