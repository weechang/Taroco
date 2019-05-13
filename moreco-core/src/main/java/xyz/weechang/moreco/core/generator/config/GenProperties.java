package xyz.weechang.moreco.core.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 代码生成配置
 *
 * @author zhangwei
 * date 2019/5/13
 * time 11:02
 */
@Data
@ConfigurationProperties(GenProperties.preKey)
public class GenProperties{

    public static final String preKey = "moreco.gen";

    /*** 扫描实体包 */
    private boolean entityScan;

    /*** 模板文件 */
    private String templates;

}
