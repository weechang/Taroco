package xyz.moreco.spring.boot.starter.config;

import xyz.moreco.spring.boot.starter.exception.CommonExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring mvc 异常映射
 *
 * @author weechang
 * date 2017年11月18日23:44:36
 */
@Configuration
public class ExceptionAutoConfig {

    @Bean
    @ConditionalOnMissingBean({CommonExceptionHandler.class})
    public CommonExceptionHandler defaultExceptionAdvice() {
        return new CommonExceptionHandler();
    }
}
