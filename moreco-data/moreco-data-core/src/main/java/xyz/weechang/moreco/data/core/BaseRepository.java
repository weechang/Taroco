package xyz.weechang.moreco.data.core;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Dao类基本方法
 *
 * @author zhangwei
 * date 2018/10/26
 * time 16:21
 */
@NoRepositoryBean
public interface BaseRepository<T> extends PagingAndSortingRepository<T, Serializable> {

}
