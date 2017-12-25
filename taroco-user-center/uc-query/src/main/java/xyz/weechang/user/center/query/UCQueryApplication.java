package xyz.weechang.user.center.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:29.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UCQueryApplication {
    public static void main(String... args) {
        SpringApplication.run(UCQueryApplication.class, args);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Override
            public String getCurrentAuditor() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication == null || !authentication.isAuthenticated()) {
                    return null;
                }

                return authentication.getName();
            }
        };
    }
}
