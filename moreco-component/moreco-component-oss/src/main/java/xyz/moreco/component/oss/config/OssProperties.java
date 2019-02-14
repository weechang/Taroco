package xyz.moreco.component.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import xyz.moreco.component.oss.config.storage.Aliyun;
import xyz.moreco.component.oss.config.storage.Qiniu;
import xyz.moreco.component.oss.config.storage.Tencent;
import xyz.moreco.component.oss.config.storage.Upyun;

/**
 * OSS config 文件
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:23
 */
@Configuration
@Data
@ConfigurationProperties("moreco.oss")
public class OssProperties {

    /*** 是否开启OSS */
    private boolean enabled;

    /*** 存储方式 */
    private String storage;

    /*** 阿里云 */
    private Aliyun aliyun;

    /*** 阿里云 */
    private Qiniu qiniu;

    /*** 阿里云 */
    private Tencent tencent;

    /*** 又拍云 */
    private Upyun Upyun;
}
