package io.github.weechang.moreco.security.config;

import io.github.weechang.moreco.security.auth.common.*;
import io.github.weechang.moreco.security.auth.jwt.JwtAuthenticationProvider;
import io.github.weechang.moreco.security.auth.jwt.JwtAuthenticationSuccessHandler;
import io.github.weechang.moreco.security.auth.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import java.util.List;

/**
 * 配置
 *
 * @author zhangwei
 * date 2019/1/26
 * time 21:17
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MorecoAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JwtAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MorecoAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private MorecoLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private MorecoAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MorecoUserDetailsService userDetailsService;
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 定义认证用户信息获取来源，密码校验规则等
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    /**
     * 在这里配置哪些页面不需要认证
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> noAuthPath = securityProperties.getNoAuthPaths();
        web.ignoring().antMatchers(noAuthPath.toArray(new String[0]));
    }

    /**
     * 定义安全策略
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                /***未登录*/
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .authorizeRequests()

                /***RBAC权限*/
                .anyRequest()
                .access("@rbacAuthorityservice.hasPermission(request,authentication)")

//                /***跨域*/
//                .and()
//                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
//                new Header("Access-control-Allow-Origin", "*"),
//                new Header("Access-Control-Expose-Headers", SecurityProperties.authKey))))
//                .and()
//                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)

                .and()
                .formLogin()
                /***登录成功*/
                .successHandler(authenticationSuccessHandler)
                /***登录失败*/
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .cors()

                .and()
                .logout()
                /***注销成功*/
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        /***记住我*/
        http.rememberMe().rememberMeParameter("rememberMe")
                .userDetailsService(userDetailsService).tokenValiditySeconds(300);

        /***无权限*/
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        /***JWT*/
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        /***跨域*/
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry
                = http.authorizeRequests();
        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();
    }
}
