package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.core.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.model.AuditEntry;
import xyz.weechang.user.center.command.dto.MenuCreateRequest;

import java.util.UUID;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:50.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class MenuCreateCommand extends AuditAbleAbstractCommand {

    private static final long serialVersionUID = 6700408375672370119L;

    @TargetAggregateIdentifier
    private String id;

    private String parentId;

    private String name;

    private String uri;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private Boolean open;

    public MenuCreateCommand(AuditEntry auditEntry, MenuCreateRequest request) {
        new MenuCreateCommand(auditEntry, request.getParentId(),
                request.getName(), request.getUri(), request.getPerms(), request.getType(),
                request.getIcon(), request.getOrderNum(), request.getOpen());
    }

    public MenuCreateCommand(
            AuditEntry auditEntry, String parentId, String name, String uri,
            String perms, Integer type, String icon, Integer orderNum, Boolean open) {
        super(auditEntry);
        new MenuCreateCommand(UUID.randomUUID().toString(), parentId,
                name, uri, perms, type, icon, orderNum, open);
    }
}
