package xyz.moreco.data.redis;

import org.springframework.data.repository.NoRepositoryBean;
import xyz.weechang.moreco.data.core.BaseRepository;

/**
 * Redis 数据库操作基础类
 *
 * @author zhangwei
 * date 2019/2/12
 * time 10:40
 */
@NoRepositoryBean
public interface RedisBaseRepository<T> extends BaseRepository<T> {
}
