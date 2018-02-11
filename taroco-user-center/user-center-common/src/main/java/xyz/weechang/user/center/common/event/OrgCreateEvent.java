package xyz.weechang.user.center.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.core.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:57.
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrgCreateEvent extends AuditableAbstractEvent {

    private static final long serialVersionUID = -3584082263472922318L;

    private String parentId;

    private String code;

    private String name;

    private Integer orderNum;

    public OrgCreateEvent(
            String id, AuditEntry auditEntry, String parentId,
            String code, String name, Integer orderNum) {
        super(id, auditEntry);
        this.parentId = parentId;
        this.code = code;
        this.name = name;
        this.orderNum = orderNum;
    }
}
