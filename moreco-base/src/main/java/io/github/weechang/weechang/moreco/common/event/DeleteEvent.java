package io.github.weechang.weechang.moreco.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.github.weechang.weechang.moreco.query.domain.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:27.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DeleteEvent extends AuditableAbstractEvent {

    private static final long serialVersionUID = 8749413633678833616L;

    protected String id;

    protected Boolean logic;

    public DeleteEvent(String id, AuditEntry auditEntry, Boolean logic) {
        this.logic = logic;
    }
}
