package xyz.weechang.user.center.command.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 注册类
 *
 * @author WeeChang
 * @version 1.0 2016/11/26 16:57
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TAROCO USER-CENTER COMMAND SIDE RESTFUL API")
                .description("TAROCO USER-CENTER COMMAND SIDE RESTFUL API")
                .contact(new Contact("WeeChang", "https://github.com/weechang", "zhangwei_sc@fomxmail.com"))
                .version("0.0.1-SNAPSHOT")
                .build();
    }

}
