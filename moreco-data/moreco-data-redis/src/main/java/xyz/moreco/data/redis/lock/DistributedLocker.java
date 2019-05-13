package xyz.moreco.data.redis.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author zhangwei
 * date 2019/5/13
 * time 15:43
 */
public interface DistributedLocker {

    void lock(String lockKey);

    void unlock(String lockKey);

    void lock(String lockKey, int timeout);

    void lock(String lockKey, TimeUnit unit , int timeout);
}
