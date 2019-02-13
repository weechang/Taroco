package xyz.weechang.moreco.component.rbac.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 自动配置
 *
 * @author zhangwei
 * date 2019/2/13
 * time 13:43
 */
@ComponentScan(value = {"xyz.weechang.moreco.component.rbac"})
@EntityScan("xyz.weechang.moreco.component.rbac")
@EnableJpaRepositories(basePackages = {"xyz.weechang.moreco.component.rbac"})
@Configuration
public class RbacAutoConfiguration {

}
