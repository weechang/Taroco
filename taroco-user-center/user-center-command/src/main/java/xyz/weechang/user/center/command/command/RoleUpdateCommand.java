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
 * @version 2017/11/20 22:50.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RoleUpdateCommand extends AuditAbleAbstractCommand {
    private static final long serialVersionUID = -2642180267304204347L;

    @TargetAggregateIdentifier
    private String id;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 10)
    private String roleName;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 10)
    private String roleSign;

    private String remark;

    public RoleUpdateCommand(AuditEntry auditEntry, RoleCreateRequest request) {
        new RoleUpdateCommand(auditEntry, request.getOrgId(), request.getRoleName(),
                request.getRoleSign(), request.getRemark());
    }

    public RoleUpdateCommand(
            AuditEntry auditEntry, String orgId, String roleName,
            String roleSign, String remark) {
        super(auditEntry);
        new RoleUpdateCommand(UUID.randomUUID().toString(), roleName, roleSign, remark);
    }
}
