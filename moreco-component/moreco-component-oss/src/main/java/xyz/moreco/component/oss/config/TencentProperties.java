package xyz.moreco.component.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云存储
 *
 * @author zhangwei
 * date 2019/2/13
 * time 16:02
 */
@Data
@ConfigurationProperties(OssProperties.tencentPreKey)
public class TencentProperties {

    /*** 腾讯云绑定的域名 */
    private String domain;

    /*** 腾讯云路径前缀 */
    private String prefix;

    /*** 腾讯云AppId */
    private Integer appId;

    /*** 腾讯云SecretId */
    private String secretId;

    /*** 腾讯云SecretKey */
    private String secretKey;

    /*** 腾讯云BucketName */
    private String bucketName;

    /*** 腾讯云COS所属地区 */
    private String region;
}
