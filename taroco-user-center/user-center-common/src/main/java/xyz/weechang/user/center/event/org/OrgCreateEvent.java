package xyz.weechang.user.center.event.org;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:57.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class OrgCreateEvent extends AuditableAbstractEvent {

    private static final long serialVersionUID = -3584082263472922318L;

    private String parentId;

    private String name;

    public OrgCreateEvent(
            String id, AuditEntry auditEntry, String parentId, String name) {
        super(id, auditEntry);
        this.parentId = parentId;
        this.name = name;
    }
}
