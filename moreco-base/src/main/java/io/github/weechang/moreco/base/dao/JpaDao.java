package io.github.weechang.moreco.base.dao;

import io.github.weechang.moreco.base.dao.support.ByExampleSpecification;
import io.github.weechang.moreco.base.dao.support.ByRangeSpecification;
import io.github.weechang.moreco.base.dao.support.Range;
import io.github.weechang.moreco.base.model.domain.BaseDomain;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

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
public interface JpaDao<T extends BaseDomain> extends BaseDao<T>, JpaRepository<T, Serializable>, JpaSpecificationExecutor {

    default Page<T> findAll(Example example, List<Range<T>> ranges, Pageable pageable) {
        Specification<T> byExample = new ByExampleSpecification<>(example);
        Specification<T> byRanges = new ByRangeSpecification(ranges);
        return findAll(Specifications.where(byExample).and(byRanges), pageable);
    }
}
