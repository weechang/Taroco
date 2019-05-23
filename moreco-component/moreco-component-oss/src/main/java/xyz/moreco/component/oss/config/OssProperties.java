package xyz.moreco.component.oss.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * OSS config 文件
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:23
 */
@Configuration
@Data
public class OssProperties {

    /*** 阿里云Key */
    public static final String aliyunPreKey = "morece.oss.aliyun";

    /*** 七牛云Key */
    public static final String qiniuPreKey = "morece.oss.qiniu";

    /*** 腾讯云Key */
    public static final String tencentPreKey = "morece.oss.tencent";

    /*** 存储方式 */
    public static final String storage = "";
}
