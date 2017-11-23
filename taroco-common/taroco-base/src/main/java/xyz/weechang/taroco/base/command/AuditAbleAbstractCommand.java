package xyz.weechang.taroco.base.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.base.model.AuditEntry;

/**
 * 可审计的命令
 *
 * @author weechang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditAbleAbstractCommand {

    private AuditEntry auditEntry;

}
