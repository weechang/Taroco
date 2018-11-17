package io.github.weechang.moreco.spring.boot.starter.config;

import io.github.weechang.moreco.spring.boot.starter.interceptor.CorsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zhangwei * date 2018/11/17
 * time 17:03
 */
@Configuration
public class CorsInterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor());
        super.addInterceptors(registry);
    }
}
