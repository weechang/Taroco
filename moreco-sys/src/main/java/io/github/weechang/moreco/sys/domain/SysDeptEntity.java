package io.github.weechang.moreco.sys.domain;

import io.github.weechang.weechang.moreco.query.domain.BaseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * 部门
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDeptEntity extends BaseEntry {
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
