package xyz.weechang.user.center.event.menu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/20 20:39.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MenuDeleteEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = -7843090532088298652L;

    private Boolean logic;

    public MenuDeleteEvent(String id, AuditEntry auditEntry, Boolean logic) {
        super(id, auditEntry);
        this.logic = logic;
    }
}
