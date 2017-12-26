package xyz.weechang.taroco.core.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.core.model.AuditEntry;

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

    private static final long serialVersionUID = 3983160461992410742L;

//    @TargetAggregateIdentifier
    private String id;

    private Boolean logic;

    public DeleteCommand(AuditEntry auditEntry, String id, Boolean logic) {
        super(auditEntry);
        if (logic == null){
            logic = true;
        }
        new DeleteCommand(id, logic);
    }
}
