package io.github.weechang.moreco.base.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * 操作人员
 *
 * @author zhangwei
 * date 2019/2/2
 * time 16:21
 */
@Configuration
public class UsernameAuditorBean implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String username = MorecoSecurityUtil.getUsername();
        username = username == null ? "SYSTEM" : username;
        return username;
    }
}
