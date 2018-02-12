package xyz.weechang.user.center.command.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/12 10:14.
 */
@Configuration
@Import({
        AxonConfig.class,
        OrgConfig.class,
        MenuConfig.class,
        RoleConfig.class,
        UserConfig.class
        }
)
public class AppConfig {
}
