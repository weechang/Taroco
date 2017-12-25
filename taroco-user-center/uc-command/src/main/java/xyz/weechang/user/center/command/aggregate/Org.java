package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.taroco.base.command.DeleteCommand;
import xyz.weechang.taroco.base.event.DeleteEvent;
import xyz.weechang.user.center.command.command.OrgCreateCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.common.event.OrgCreateEvent;
import xyz.weechang.user.center.common.event.OrgUpdateEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:22.
 */
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Org{

    @AggregateIdentifier
    private String id;

    private String parentId;

    private String code;

    private String name;

    private Integer orderNum;

    private Boolean enable;

    private Boolean open;

    public Org(OrgCreateCommand command) {
        apply(new OrgCreateEvent(
                command.getId(), command.getAuditEntry(), command.getParentId(),
                command.getCode(), command.getName(), command.getOrderNum(),
                command.getEnable(), command.getOpen()));
    }

    public void update(OrgUpdateCommand command) {
        apply(new OrgUpdateEvent(
                command.getId(), command.getAuditEntry(), command.getCode(),
                command.getName(), command.getOrderNum(), command.getEnable(),
                command.getOpen()));
    }

    public void delete(DeleteCommand command) {
        apply(new DeleteEvent(command.getId(), command.getAuditEntry(), command.getLogic()));
    }

    @EventSourcingHandler
    public void on(OrgCreateEvent event) {
        this.id = event.getId();
        this.parentId = event.getParentId();
        this.code = event.getCode();
        this.name = event.getName();
        this.orderNum = event.getOrderNum();
        this.enable = event.getEnable();
        this.open = event.getOpen();
    }

    @EventSourcingHandler
    public void on(OrgUpdateEvent event) {
        this.id = event.getId();
        this.code = event.getCode();
        this.name = event.getName();
        this.orderNum = event.getOrderNum();
        this.enable = event.getEnable();
        this.open = event.getOpen();
    }

    @EventSourcingHandler
    public void on(DeleteCommand event) {
        this.id = event.getId();
    }
}
