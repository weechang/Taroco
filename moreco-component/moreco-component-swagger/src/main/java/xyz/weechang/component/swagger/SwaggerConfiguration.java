package xyz.weechang.component.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 * swagger注解
 *
 * @author zhangwei
 * date 2019/2/12
 * time 11:30
 */
@Configuration
@ConditionalOnProperty(name = SwaggerConfiguration.enableKey, matchIfMissing = true)
@Import({
        Swagger2DocumentationConfiguration.class
})
public class SwaggerConfiguration {

    /***前缀key*/
    public static final String preKey = "moreco.swagger";

    /***可用key*/
    public static final String enableKey = "moreco.swagger.enabled";
}
