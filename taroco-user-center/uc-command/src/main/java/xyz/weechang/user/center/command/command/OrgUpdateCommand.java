package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.NotEmpty;
import xyz.weechang.taroco.base.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.base.model.AuditEntry;
import xyz.weechang.user.center.command.dto.OrgUpdateRequest;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:44.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class OrgUpdateCommand extends AuditAbleAbstractCommand {

    private static final long serialVersionUID = -541440347177245718L;
    @TargetAggregateIdentifier
    private String id;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    private Integer orderNum;

    private Boolean enable;

    private Boolean open;

    public OrgUpdateCommand(AuditEntry auditEntry, String id, OrgUpdateRequest request){
        new OrgUpdateCommand(auditEntry, id, request.getCode(), request.getName(),
                request.getOrderNum(), request.getEnable(), request.getOpen());
    }

    public OrgUpdateCommand(
            AuditEntry auditEntry, String id, String code, String name,
            Integer orderNum, Boolean enable, Boolean open) {
        super(auditEntry);
        new OrgUpdateCommand(id, code, name, orderNum, enable, open);
    }
}
