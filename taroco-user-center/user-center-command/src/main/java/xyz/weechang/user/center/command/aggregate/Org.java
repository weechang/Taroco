package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.taroco.core.command.aggregate.BaseAggregate;
import xyz.weechang.user.center.command.command.OrgCreateCommand;
import xyz.weechang.user.center.command.command.OrgDeleteCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.event.org.OrgCreateEvent;
import xyz.weechang.user.center.event.org.OrgDeleteEvent;
import xyz.weechang.user.center.event.org.OrgUpdateEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:22.
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Org extends BaseAggregate {

    private static final long serialVersionUID = 1753809583586091219L;

    @AggregateIdentifier
    private String id;

    private String parentId;

    private String code;

    private String name;

    private Integer orderNum = 1;

    public Org(OrgCreateCommand command) {
        OrgCreateEvent event = new OrgCreateEvent(
                command.getId(), command.getAuditEntry(),
                command.getParentId(), command.getName());
        apply(event);
    }

    public void update(OrgUpdateCommand command) {
        OrgUpdateEvent event = new OrgUpdateEvent(
                command.getId(), command.getAuditEntry(),
                command.getName(), command.getOrderNum());
        apply(event);
    }

    public void delete(OrgDeleteCommand command) {
       OrgDeleteEvent event = new OrgDeleteEvent(
                command.getId(), command.getAuditEntry(), command.getLogic());
        apply(event);
    }

    @EventSourcingHandler
    public void on(OrgCreateEvent event) {
        this.id = event.getId();
        this.parentId = event.getParentId();
        this.name = event.getName();
    }

    @EventSourcingHandler
    public void on(OrgUpdateEvent event) {
        this.name = event.getName();
        this.orderNum = event.getOrderNum();
    }

    @EventSourcingHandler
    public void on(OrgDeleteEvent event) {
        if (event.getLogic()) {
            super.deleted = true;
        } else {
            // TODO: 2018/2/20 deleted
        }
    }
}
