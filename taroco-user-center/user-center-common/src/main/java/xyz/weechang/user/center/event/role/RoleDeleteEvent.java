package xyz.weechang.user.center.event.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/20 22:10.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDeleteEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = -345544909100121688L;

    private Boolean logic;

    public RoleDeleteEvent(String id, AuditEntry auditEntry, Boolean logic) {
        super(id, auditEntry);
        this.logic = logic;
    }
}
