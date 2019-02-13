package xyz.weechang.moreco.data.jpa.support;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 18:06
 */
public class ByExampleSpecification<T> implements Specification<T> {
    private static final long serialVersionUID = 8554242591333108949L;
    private final Example<T> example;

    public ByExampleSpecification(@NonNull Example<T> example) {
        this.example = example;
    }

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder cb) {
        return QueryByExamplePredicateBuilder.getPredicate(root, cb, example);
    }
}