package xyz.moreco.component.oss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置
 *
 * @author zhangwei
 * date 2019/2/13
 * time 15:51
 */
@Configuration
@Import({
        OssProperties.class
})
public class OSSAutoConfiguration {
}
