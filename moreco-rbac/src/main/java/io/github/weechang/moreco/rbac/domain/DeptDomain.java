package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.EntityGraph;

/**
 * 部门
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class DeptDomain extends BaseDomain {
    private static final long serialVersionUID = 1230574664359885255L;

    /**
     * 部门id
     */
    @Id
    private Long id;
    /**
     * 上级部门ID，一级部门为0
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer orderNum;
}
