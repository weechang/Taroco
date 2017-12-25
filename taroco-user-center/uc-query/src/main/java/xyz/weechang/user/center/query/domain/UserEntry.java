package xyz.weechang.user.center.query.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.base.model.BaseEntry;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * 说明：用户
 *
 * @author zhangwei
 * @version 2017/11/4 13:42.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class UserEntry extends BaseEntry {

    private static final long serialVersionUID = -4981003098175348218L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮件
     */
    private String email;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 所有的角色
     */
    @ManyToMany(targetEntity = RoleEntry.class, fetch = FetchType.LAZY)
    private List<RoleEntry> roles;
}
