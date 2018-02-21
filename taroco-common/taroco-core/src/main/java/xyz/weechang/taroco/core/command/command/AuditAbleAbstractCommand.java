package xyz.weechang.taroco.core.command.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.weechang.taroco.core.query.domain.AuditEntry;

import java.io.Serializable;

/**
 * 可审计的命令
 *
 * @author weechang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditAbleAbstractCommand implements Serializable{

    private static final long serialVersionUID = 6905093076516628362L;
    private AuditEntry auditEntry;

}
