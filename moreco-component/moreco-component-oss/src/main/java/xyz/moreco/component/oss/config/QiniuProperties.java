package xyz.moreco.component.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 七牛云存储
 *
 * @author zhangwei
 * date 2019/2/13
 * time 16:01
 */
@Data
@ConfigurationProperties(OssProperties.qiniuPreKey)
public class QiniuProperties {

    /*** 七牛绑定域名 */
    private String domain;

    /*** 七牛路径前缀 */
    private String prefix;

    /*** 七牛ACCESS_KEY */
    private String accessKey;

    /*** 七牛SECRET_KEY */
    private String secretKey;

    /*** 七牛存储空间名 */
    private String bucketName;

}
