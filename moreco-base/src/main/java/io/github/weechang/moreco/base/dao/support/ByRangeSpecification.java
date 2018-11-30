package io.github.weechang.moreco.base.dao.support;

import com.google.common.collect.Lists;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 18:08
 */
public class ByRangeSpecification<T> implements Specification<T> {

    private final List<Range<T>> ranges;

    public ByRangeSpecification(List<Range<T>> ranges) {
        this.ranges = ranges;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = Lists.newArrayList();

        for (Range<T> range : ranges) {
            if (range.isSet()) {
                Predicate rangePredicate = buildRangePredicate(range, root, builder);

                if (rangePredicate != null) {
                    if (!range.isIncludeNullSet() || range.getIncludeNull() == FALSE) {
                        predicates.add(rangePredicate);
                    } else {
                        predicates.add(builder.or(rangePredicate, builder.isNull(root.get(range.getField()))));
                    }
                }

                if (TRUE == range.getIncludeNull()) {
                    predicates.add(builder.isNull(root.get(range.getField())));
                } else if (FALSE == range.getIncludeNull()) {
                    predicates.add(builder.isNotNull(root.get(range.getField())));
                }
            }
        }
        Predicate[] predicateArray = (Predicate[]) predicates.toArray();
        return predicates.isEmpty() ? builder.conjunction() : builder.and(predicateArray);
    }

    private Predicate buildRangePredicate(Range<T> range, Root<T> root, CriteriaBuilder builder) {
        if (range.isBetween()) {
            return builder.between(root.get(range.getField()), range.getFrom(), range.getTo());
        } else if (range.isFromSet()) {
            return builder.greaterThanOrEqualTo(root.get(range.getField()), range.getFrom());
        } else if (range.isToSet()) {
            return builder.lessThanOrEqualTo(root.get(range.getField()), range.getTo());
        }
        return null;
    }

}