package xyz.weechang.moreco.data.jpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import xyz.weechang.moreco.data.core.BaseRepository;
import xyz.weechang.moreco.data.jpa.support.ByExampleSpecification;
import xyz.weechang.moreco.data.jpa.support.ByRangeSpecification;
import xyz.weechang.moreco.data.jpa.support.Range;

import java.io.Serializable;
import java.util.List;


/**
 * JPA dao
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:01
 */
@NoRepositoryBean
public interface JpaBaseRepository<T> extends BaseRepository<T>, JpaRepository<T, Serializable>, JpaSpecificationExecutor {

    default Page<T> findAll(Example example, List<Range<T>> ranges, Pageable pageable) {
        Specification<T> byExample = new ByExampleSpecification<>(example);
        Specification<T> byRanges = new ByRangeSpecification(ranges);
        return findAll(Specification.where(byExample).and(byRanges), pageable);
    }

}
