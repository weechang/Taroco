package xyz.weechang.user.center.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.base.event.AuditAbleAbstractEvent;
import xyz.weechang.taroco.base.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:57.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrgCreateEvent extends AuditAbleAbstractEvent {

    private static final long serialVersionUID = -3584082263472922318L;

    private String parentId;

    private String code;

    private String name;

    private Integer orderNum;

    private Boolean enable;

    private Boolean open;

    public OrgCreateEvent(
            String id, AuditEntry auditEntry, String parentId,
            String code, String name, Integer orderNum,
            Boolean enable, Boolean open) {
        super(id, auditEntry);
        new OrgCreateEvent(parentId, code, name, orderNum, enable, open);
    }
}
