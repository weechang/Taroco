package xyz.weechang.user.center.query.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import xyz.weechang.taroco.core.model.BaseEntry;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * 说明：组织机构
 *
 * @author zhangwei
 * @version 2017/11/4 13:44.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
public class OrgEntry extends BaseEntry {

    private static final long serialVersionUID = 2136457038173964553L;

    /**
     * 上级机构
     */
    private OrgEntry parent;

    /**
     * 机构编码
     */
    private String code;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 可用标识，1：可用，0：不可用
     */
    private Boolean enable;

    /**
     * ztree属性
     */
    private Boolean open;

    /**
     * 所有的角色
     */
    @ManyToMany(targetEntity = RoleEntry.class, fetch = FetchType.LAZY)
    private List<RoleEntry> roles;
}
