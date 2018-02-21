package xyz.weechang.user.center.event.role;

import lombok.AllArgsConstructor;
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
 * @version 2017/11/20 11:19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RoleCreateEvent extends AuditableAbstractEvent {

    private static final long serialVersionUID = 4917983558568684287L;

    private String name;

    private String remark;

    private List<String> menus;

    public RoleCreateEvent(
            String id, AuditEntry auditEntry, String name,
            String remark, List<String> menus) {
        super(id, auditEntry);
        this.name = name;
        this.remark = remark;
        this.menus = menus;
    }
}
