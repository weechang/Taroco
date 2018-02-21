package xyz.weechang.user.center.event.menu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.common.event.AuditableAbstractEvent;
import xyz.weechang.taroco.core.query.domain.AuditEntry;
import xyz.weechang.user.center.enums.MenuType;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:53.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class MenuCreateEvent extends AuditableAbstractEvent {
    private static final long serialVersionUID = -1853894163416288688L;

    private String parentId;

    private String name;

    private String uri;

    private String perms;

    private MenuType type;

    private String icon;

    public MenuCreateEvent(
            String id, AuditEntry auditEntry, String name,
            String uri, String perms, MenuType type, String icon){
        super(id, auditEntry);
        this.parentId = parentId;
        this.name = name;
        this.uri = uri;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
    }

}
