package xyz.weechang.taroco.core.event;


import xyz.weechang.taroco.core.model.AuditEntry;

/**
 * 可审计的抽象事件
 *
 * @author weechang
 */
public abstract class AuditableAbstractEvent extends AbstractEvent {

    private static final long serialVersionUID = -5389550139760061559L;

    private AuditEntry auditEntry;

    public AuditableAbstractEvent() {

    }

    public AuditableAbstractEvent(String id, AuditEntry auditEntry) {
        super(id);
        this.setAuditEntry(auditEntry);
    }

    public AuditEntry getAuditEntry() {
        return auditEntry;
    }

    public void setAuditEntry(AuditEntry auditEntry) {
        this.auditEntry = auditEntry;
    }
}
