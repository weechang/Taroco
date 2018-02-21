package xyz.weechang.user.center.event.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

import java.util.List;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:53.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleUpdateEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = 8836324219868424172L;

    private String name;

    private String remark;

    private List<String> menus;

    public RoleUpdateEvent(
            String id, AuditEntry auditEntry, String name,
            String remark, List<String> menus) {
        super(id, auditEntry);
        this.name = name;
        this.remark = remark;
        this.menus = menus;
    }
}
