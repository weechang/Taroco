package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.user.center.command.command.RoleCreateCommand;
import xyz.weechang.user.center.common.event.RoleCreateEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:28.
 */
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Role {

    @AggregateIdentifier
    private String id;

    private String orgId;

    private String roleName;

    private String roleSign;

    private String remark;

    public Role(RoleCreateCommand command) {
        apply(new RoleCreateEvent(
                command.getId(), command.getAuditEntry(), command.getOrgId(),
                command.getRoleName(), command.getRoleSign(), command.getRemark()));
    }
}
