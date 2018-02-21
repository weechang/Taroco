package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import xyz.weechang.taroco.core.command.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.query.domain.AuditEntry;
import xyz.weechang.user.center.command.dto.RoleCreateRequest;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 11:23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RoleCreateCommand extends AuditAbleAbstractCommand {

    private static final long serialVersionUID = 9111256469947176260L;

    @TargetAggregateIdentifier
    private String id;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 10)
    private String name;

    private String remark;

    private List<String> menus;

    public RoleCreateCommand(AuditEntry auditEntry, RoleCreateRequest request) {
        this.id = UUID.randomUUID().toString();
        this.name = request.getName();
        this.remark = request.getRemark();
        this.menus = request.getMenus();
    }
}
