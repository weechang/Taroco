package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.base.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.base.model.AuditEntry;
import xyz.weechang.user.center.command.dto.MenuUpdateRequest;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:50.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MenuUpdateCommand extends AuditAbleAbstractCommand {
    private static final long serialVersionUID = 4663752036609691494L;

    @TargetAggregateIdentifier
    private String id;

    private String name;

    private String uri;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private Boolean open;

    public MenuUpdateCommand(AuditEntry auditEntry, String id, MenuUpdateRequest request) {
        new MenuUpdateCommand(auditEntry, id, request.getName(), request.getUri(),
                request.getPerms(), request.getType(), request.getIcon(),
                request.getOrderNum(), request.getOpen());
    }

    public MenuUpdateCommand(
            AuditEntry auditEntry, String id, String name, String uri,
            String perms, Integer type, String icon, Integer orderNum, Boolean open) {
        super(auditEntry);
        new MenuUpdateCommand(id, name, uri, perms, type, icon, orderNum, open);
    }
}
