package xyz.weechang.user.center.event.org;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/20 22:09.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrgDeleteEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = 6348987459823456302L;

    private Boolean logic;

    public OrgDeleteEvent(String id, AuditEntry auditEntry, Boolean logic) {
        super(id, auditEntry);
        this.logic = logic;
    }
}
