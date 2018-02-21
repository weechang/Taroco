package xyz.weechang.taroco.core.common.event;

import xyz.weechang.taroco.core.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:27.
 */
public class CreateEvent extends AuditableAbstractEvent {

    public CreateEvent(String id, AuditEntry auditEntry) {
        super(id, auditEntry);
    }
}
