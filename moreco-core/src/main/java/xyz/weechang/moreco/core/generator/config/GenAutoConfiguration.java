package xyz.weechang.moreco.core.generator.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author zhangwei
 * date 2019/2/13
 * time 13:43
 */
@ComponentScan(value = {"xyz.weechang.moreco.core.generator"})
//@EntityScan(basePackages = "xyz.weechang.moreco")
//@EnableJpaRepositories(
//        basePackages = {"xyz.weechang.moreco"},
//        repositoryFactoryBeanClass = JpaBaseRepositoryFactoryBean.class)
@Configuration
public class GenAutoConfiguration {

}
