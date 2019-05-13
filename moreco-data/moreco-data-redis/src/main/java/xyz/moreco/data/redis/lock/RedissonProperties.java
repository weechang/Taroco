package xyz.moreco.data.redis.lock;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redisson 配置文件
 *
 * @author zhangwei
 * date 2019/5/13
 * time 15:49
 */
@Data
@ConfigurationProperties(prefix = RedissonProperties.preKey)
public class RedissonProperties {

    /*** 配置文件前缀 */
    public static final String preKey = "moreco.redisson";

    /*** 哨兵模式 */
    public static final String sentinelKey = "moreco.redisson.master";

    /*** 单兵模式KEY */
    public static final String singleKey = "moreco.redisson.address";

    private int timeout = 3000;

    private String address;

    private String password;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize=10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;
}
