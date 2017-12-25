package xyz.weechang.user.center.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 说明：用户中心 命令端
 *
 * @author zhangwei
 * @version 2017/11/5 22:42.
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class UCCommandApplication {

    public static void main(String... args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(UCCommandApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "UCCommandApplication '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
}
