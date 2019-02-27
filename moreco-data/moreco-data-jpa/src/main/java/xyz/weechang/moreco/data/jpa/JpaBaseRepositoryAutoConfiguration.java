package xyz.weechang.moreco.data.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 指定自定义的 jpa 工厂类
 *
 * @author zhangwei
 * date 2019/2/19
 * time 17:52
 */
@Configuration
@EnableJpaRepositories(
        repositoryFactoryBeanClass = JpaBaseRepositoryFactoryBean.class)
public class JpaBaseRepositoryAutoConfiguration {
}
