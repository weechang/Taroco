package xyz.weechang.user.center.command.command;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.core.command.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/22 10:09.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDeleteCommand extends AuditAbleAbstractCommand {
    private static final long serialVersionUID = -1221858785407599578L;

    @TargetAggregateIdentifier
    private String id;

    private Boolean logic = true;

    public UserDeleteCommand(AuditEntry auditEntry, String id, Boolean logic){
        super(auditEntry);
        this.id = id;
        this.logic = (logic == null ? true : logic);
    }
}
