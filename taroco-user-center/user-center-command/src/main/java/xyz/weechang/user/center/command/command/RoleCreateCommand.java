package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import xyz.weechang.taroco.core.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.model.AuditEntry;
import xyz.weechang.user.center.command.dto.RoleCreateRequest;

import javax.validation.constraints.NotNull;
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
    private String orgId;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 10)
    private String roleName;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 10)
    private String roleSign;

    private String remark;

    public RoleCreateCommand(AuditEntry auditEntry, RoleCreateRequest request) {
        new RoleCreateCommand(auditEntry, request.getOrgId(), request.getRoleName(),
                request.getRoleSign(), request.getRemark());
    }

    public RoleCreateCommand(
            AuditEntry auditEntry, String orgId, String roleName,
            String roleSign, String remark) {
        super(auditEntry);
        new RoleCreateCommand(UUID.randomUUID().toString(), orgId,
                roleName, roleSign, remark);
    }
}
