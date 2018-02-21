package xyz.weechang.user.center.event.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

import java.util.List;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:54.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = 6934728592504351920L;

    private String username;

    private String password;

    private String phone;

    private String email;

    private List<String> orgs;

    private List<String> roles;

    public UserCreateEvent(
            String id, AuditEntry auditEntry, String username,
            String password, String phone, String email,
            List<String> orgs, List<String> roles) {
        super(id, auditEntry);
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.orgs = orgs;
        this.roles = roles;
    }
}
