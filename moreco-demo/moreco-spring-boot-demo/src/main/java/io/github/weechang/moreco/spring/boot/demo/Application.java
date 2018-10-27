package io.github.weechang.moreco.spring.boot.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 13:29
 */
@Slf4j
@SpringBootApplication
public class Application{

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
    }
}
