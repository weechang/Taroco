package io.github.weechang.moreco.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心
 *
 * @author zhangwei
 * date 2019/2/3
 * time 21:34
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class MorecoConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MorecoConfigApplication.class, args);
    }
}
