package xyz.moreco.cloud.demoa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author zhangwei
 * date 2019/2/11
 * time 15:22
 */
@Slf4j
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "io.github.weechang.moreco")
@EnableDiscoveryClient
@SpringBootApplication
public class MorecoSpringCloudDemoA {

    public static void main(String[] args) {
        SpringApplication.run(MorecoSpringCloudDemoA.class, args);
    }
}
