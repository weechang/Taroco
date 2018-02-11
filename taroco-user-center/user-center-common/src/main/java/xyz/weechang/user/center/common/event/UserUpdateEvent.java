package xyz.weechang.user.center.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.event.AuditableAbstractEvent;
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
public class UserUpdateEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = -7437741439566351707L;

    private String password;

    private String phone;

    private String email;

    private Boolean enable;

    public UserUpdateEvent(
            String id, AuditEntry auditEntry ,String password,
            String phone, String email, Boolean enable) {
        super(id, auditEntry);
        new UserUpdateEvent(password, phone, email, enable);
    }
}
