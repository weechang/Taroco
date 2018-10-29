package io.github.weechang.moreco.rbac.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 角色
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
@Where(clause = "yn = 1")
public class RbacRole extends BaseDomain {
    private static final long serialVersionUID = -6369262328565896728L;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;
}
