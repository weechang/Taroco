package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.NotEmpty;
import xyz.weechang.taroco.core.command.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.query.domain.AuditEntry;
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

    public OrgUpdateCommand(AuditEntry auditEntry, String id, OrgUpdateRequest request){
        super(auditEntry);
        this.id = id;
        this.name = request.getName();
        this.orderNum = request.getOrderNum();
    }
}
