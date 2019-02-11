package io.github.weechang.moreco.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关
 *
 * @author zhangwei
 * date 2019/2/3
 * time 22:32
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class MorecoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MorecoGatewayApplication.class, args);
    }
}
