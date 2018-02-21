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
public class UserUpdateEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = -7437741439566351707L;

    private String password;

    private String phone;

    private String email;

    private List<String> orgs;

    private List<String> roles;

    public UserUpdateEvent(
            String id, AuditEntry auditEntry ,String password,
            String phone, String email, List<String> orgs, List<String> roles) {
        super(id, auditEntry);
       this.password = password;
       this.phone = phone;
       this.email = email;
       this.orgs = orgs;
       this.roles = roles;
    }
}
