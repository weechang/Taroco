package xyz.weechang.user.center.command.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.core.command.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.query.domain.AuditEntry;
import xyz.weechang.user.center.command.dto.MenuCreateRequest;
import xyz.weechang.user.center.enums.MenuType;

import java.util.UUID;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:50.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MenuCreateCommand extends AuditAbleAbstractCommand {

    private static final long serialVersionUID = 6700408375672370119L;

    @TargetAggregateIdentifier
    private String id;

    private String parentId;

    private String name;

    private String uri;

    private String perms;

    private MenuType type;

    private String icon;

    public MenuCreateCommand(AuditEntry auditEntry, MenuCreateRequest request) {
        super(auditEntry);
        this.id = UUID.randomUUID().toString();
        this.parentId = request.getParentId();
        this.name = request.getName();
        this.uri = request.getUri();
        this.perms = request.getPerms();
        this.type = request.getType();
        this.icon = request.getIcon();
    }
}
