package xyz.weechang.user.center.event.org;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:03.
 */
@Data
@NoArgsConstructor
public class OrgUpdateEvent extends AuditableAbstractEvent{

    private static final long serialVersionUID = -3618294635402102769L;

    private String name;

    private Integer orderNum;

    public OrgUpdateEvent(
            String id, AuditEntry auditEntry, String name, Integer orderNum) {
        super(id, auditEntry);
        this.name = name;
        this.orderNum = orderNum;
    }
}
