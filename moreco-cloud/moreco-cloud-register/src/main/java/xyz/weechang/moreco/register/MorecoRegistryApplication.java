package xyz.weechang.moreco.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心
 *
 * @author zhangwei
 * date 2019/2/3
 * time 22:25
 */
@EnableEurekaServer
@SpringBootApplication
public class MorecoRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MorecoRegistryApplication.class, args);
    }
}
