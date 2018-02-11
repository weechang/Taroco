package xyz.weechang.taroco.core.event;

import xyz.weechang.taroco.core.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:27.
 */
public class UpdateEvent extends AuditableAbstractEvent {
    public UpdateEvent(String id, AuditEntry auditEntry) {
        super(id, auditEntry);
    }
}
