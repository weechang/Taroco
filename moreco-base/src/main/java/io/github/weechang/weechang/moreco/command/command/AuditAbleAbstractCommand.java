package io.github.weechang.weechang.moreco.command.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.weechang.weechang.moreco.query.domain.AuditEntry;

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
