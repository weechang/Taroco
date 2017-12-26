package xyz.weechang.user.center.query.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.core.model.BaseEntry;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * 说明：角色
 *
 * @author zhangwei
 * @version 2017/11/5 20:08.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class RoleEntry extends BaseEntry {

    private static final long serialVersionUID = 574404246785708472L;

    /**
     * 所属机构
     */
    private OrgEntry org;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色标识
     */
    private String roleSign;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所有的目录
     */
    @ManyToMany(targetEntity = MenuEntry.class, fetch = FetchType.LAZY)
    private List<MenuEntry> menus;
}
