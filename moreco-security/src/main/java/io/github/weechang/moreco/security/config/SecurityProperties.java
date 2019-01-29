package io.github.weechang.moreco.security.config;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import lombok.Data;
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

    /***不需要授权的路径*/
    private List<String> noAuthPaths;

    @PostConstruct
    public void init(){
        noAuthPaths = CollectionUtil.isEmpty(noAuthPaths) ? Lists.newArrayList() : noAuthPaths;
    }

}
