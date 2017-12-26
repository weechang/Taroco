package xyz.weechang.taroco.core.event;


import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.model.AuditEntry;

/**
 * 可审计的抽象事件
 *
 * @author weechang
 */
@Data
@NoArgsConstructor
public abstract class AuditAbleAbstractEvent extends AbstractEvent {

    private static final long serialVersionUID = -5389550139760061559L;

    private AuditEntry auditEntry;

    public AuditAbleAbstractEvent(String id, AuditEntry auditEntry) {
        super(id);
        this.setAuditEntry(auditEntry);
    }
}
