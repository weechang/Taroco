package io.github.weechang.moreco.cloud.gateway;

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
