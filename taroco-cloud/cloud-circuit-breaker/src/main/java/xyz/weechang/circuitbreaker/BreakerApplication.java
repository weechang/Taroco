package xyz.weechang.circuitbreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 服务熔断
 *
 * @author weechang
 */
@SpringBootApplication
@EnableHystrixDashboard
public class BreakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakerApplication.class, args);
    }
}
