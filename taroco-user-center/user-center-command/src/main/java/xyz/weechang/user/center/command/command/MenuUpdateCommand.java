package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.core.command.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.query.domain.AuditEntry;
import xyz.weechang.user.center.command.dto.MenuUpdateRequest;
import xyz.weechang.user.center.enums.MenuType;

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

    private MenuType type;

    private String icon;

    private Integer orderNum;

    public MenuUpdateCommand(AuditEntry auditEntry, String id, MenuUpdateRequest request) {
        super(auditEntry);
        this.id = id;
        this.name = request.getName();
        this.uri = request.getUri();
        this.perms = request.getPerms();
        this.type = request.getType();
        this.icon = request.getIcon();
        this.orderNum = request.getOrderNum();
    }

}
