package io.github.weechang.moreco.security.config;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Security 配置文件
 *
 * @author zhangwei
 * date 2019/1/26
 * time 20:59
 */
@Data
@Component
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {

    /***
     * 系统配置敞亮
     */
    /***配置前缀*/
    public static final String PREFIX = "moreco.security";

    /***未登录路径*/
    public static final String unLoginPath = "/unLogin";

    /***登录成功路径*/
    public static final String loginSuccess = "/loginSuccess";

    /***登录失败路径*/
    public static final String loginFailure = "/loginFailure";

    /***权限不够*/
    public static final String accessDenied = "/accessDenied";

    /***授权key*/
    public static final String authKey = "auth";

    /***不需要授权的路径*/
    private List<String> noAuthPaths;

    @PostConstruct
    public void init(){
        noAuthPaths = CollectionUtils.isEmpty(noAuthPaths) ? Lists.newArrayList() : noAuthPaths;
        noAuthPaths.add(unLoginPath);
        noAuthPaths.add(loginFailure);
        noAuthPaths.add("/favicon.ico");
    }

}
