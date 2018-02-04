package xyz.weechang.user.center.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.event.AuditAbleAbstractEvent;
import xyz.weechang.taroco.core.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 11:19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateEvent extends AuditAbleAbstractEvent {

    private static final long serialVersionUID = 4917983558568684287L;

    private String orgId;

    private String roleName;

    private String roleSign;

    private String remark;

    public RoleCreateEvent(
            String id, AuditEntry auditEntry, String orgId,
            String roleName, String roleSign, String remark) {
        super(id, auditEntry);
        new RoleCreateEvent(orgId, roleName, roleSign, remark);
    }
}
