package xyz.weechang.user.center.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.event.AuditAbleAbstractEvent;
import xyz.weechang.taroco.core.model.AuditEntry;

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
public class UserCreateEvent extends AuditAbleAbstractEvent {
    private static final long serialVersionUID = 6934728592504351920L;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Boolean enable;

    public UserCreateEvent(
            String id, AuditEntry auditEntry, String username,
            String password, String phone, String email, Boolean enable) {
        super(id, auditEntry);
        new UserCreateEvent(username, password, phone, email, enable);
    }
}
