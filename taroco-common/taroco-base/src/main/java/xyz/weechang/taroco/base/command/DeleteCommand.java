package xyz.weechang.taroco.base.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.base.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:26.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class DeleteCommand extends AuditAbleAbstractCommand  {

    @TargetAggregateIdentifier
    private String id;

    private Boolean logic;

    public DeleteCommand(AuditEntry auditEntry,String id, Boolean logic) {
        super(auditEntry);
        new DeleteCommand(id, logic);
    }
}
