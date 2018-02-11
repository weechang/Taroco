package xyz.weechang.taroco.core.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.core.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:27.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class DeleteEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = 8749413633678833616L;

    private Boolean logic;

    public DeleteEvent(String id, AuditEntry auditEntry, Boolean logic) {
        super(id, auditEntry);
        this.logic = logic;
    }
}
