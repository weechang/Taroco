package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.NotEmpty;
import xyz.weechang.taroco.core.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.model.AuditEntry;
import xyz.weechang.user.center.command.dto.OrgCreateRequest;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:37.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class OrgCreateCommand extends AuditAbleAbstractCommand {

    private static final long serialVersionUID = 8693514247658926855L;

    @TargetAggregateIdentifier
    private String id;

    private String parentId;

    @NotNull
    @NotEmpty
    private String code;

    @NotNull
    @NotEmpty
    private String name;

    private Integer orderNum = 1;

    private Boolean enable = true;

    private Boolean open = false;

    public OrgCreateCommand(AuditEntry auditEntry, OrgCreateRequest request) {
        new OrgCreateCommand(auditEntry, request.getParentId(), request.getCode(),
                request.getName(), request.getOrderNum(), request.getEnable(), request.getOpen());
    }

    public OrgCreateCommand(
            AuditEntry auditEntry, String parentId, String code, String name,
            Integer orderNum, Boolean enable, Boolean open) {
        super(auditEntry);
        new OrgCreateCommand(UUID.randomUUID().toString(), parentId,
                code, name, orderNum, enable, open);
    }
}
