package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.taroco.core.command.aggregate.BaseAggregate;
import xyz.weechang.taroco.core.command.command.DeleteCommand;
import xyz.weechang.user.center.command.command.RoleCreateCommand;
import xyz.weechang.user.center.command.command.RoleUpdateCommand;
import xyz.weechang.user.center.event.role.RoleCreateEvent;
import xyz.weechang.user.center.event.role.RoleDeleteEvent;
import xyz.weechang.user.center.event.role.RoleUpdateEvent;

import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:28.
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Role extends BaseAggregate {

    private static final long serialVersionUID = 8355915077746301350L;
    @AggregateIdentifier
    private String id;

    private String name;

    private String remark;

    private List<String> menus;

    public Role(RoleCreateCommand command) {
        RoleCreateEvent event = new RoleCreateEvent(
                command.getId(), command.getAuditEntry(),
                command.getName(), command.getRemark(), command.getMenus());
        apply(event);
    }

    public void update(RoleUpdateCommand command) {
        RoleUpdateEvent event = new RoleUpdateEvent(
                command.getId(), command.getAuditEntry(),
                command.getName(), command.getRemark(), command.getMenus());
        apply(event);
    }

    public void delete(DeleteCommand command) {
        RoleDeleteEvent event = new RoleDeleteEvent(
                command.getId(), command.getAuditEntry(), command.getLogic());
        apply(event);
    }

    @EventSourcingHandler
    public void on(RoleCreateEvent event){
        this.id = event.getId();
        this.name = event.getName();
        this.remark = event.getRemark();
        this.menus = event.getMenus();
    }

    @EventSourcingHandler
    public void on(RoleUpdateEvent event){
        this.name = event.getName();
        this.remark = event.getRemark();
        this.menus = event.getMenus();
    }

    @EventSourcingHandler
    public void on(RoleDeleteEvent event){
        if (event.getLogic()) {
            super.deleted = true;
        } else {
            // TODO: 2018/2/20 deleted
        }
    }
}
