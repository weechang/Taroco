package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.taroco.core.command.aggregate.BaseAggregate;
import xyz.weechang.user.center.command.command.MenuCreateCommand;
import xyz.weechang.user.center.command.command.MenuDeleteCommand;
import xyz.weechang.user.center.command.command.MenuUpdateCommand;
import xyz.weechang.user.center.enums.MenuType;
import xyz.weechang.user.center.event.menu.MenuCreateEvent;
import xyz.weechang.user.center.event.menu.MenuDeleteEvent;
import xyz.weechang.user.center.event.menu.MenuUpdateEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:51.
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Menu extends BaseAggregate{

    private static final long serialVersionUID = -4779437388385030954L;

    @AggregateIdentifier
    private String id;

    private String parentId;

    private String name;

    private String uri;

    private String perms;

    private MenuType type = MenuType.MENU;

    private String icon;

    private Integer orderNum = 1;

    public Menu(MenuCreateCommand command) {
        MenuCreateEvent event = new MenuCreateEvent(
                command.getId(), command.getAuditEntry(), command.getName(),
                command.getUri(), command.getPerms(), command.getType(), command.getIcon()
        );
        apply(event);
    }

    public void update(MenuUpdateCommand command) {
        MenuUpdateEvent event = new MenuUpdateEvent(
                command.getId(), command.getAuditEntry(), command.getName(),
                command.getPerms(), command.getType(), command.getIcon(), command.getOrderNum()
        );
        apply(event);
    }

    public void delete(MenuDeleteCommand command){
        MenuDeleteEvent event = new MenuDeleteEvent(command.getId(), command.getAuditEntry(), command.getLogic());
        apply(event);
    }

    @EventSourcingHandler
    public void on(MenuCreateEvent event) {
        this.id = event.getId();
        this.parentId = event.getParentId();
        this.name = event.getName();
        this.uri = event.getUri();
        this.perms = event.getPerms();
        this.type = event.getType();
        this.icon = event.getIcon();
    }

    @EventSourcingHandler
    public void on(MenuUpdateEvent event){
        this.id = event.getId();
        this.name = event.getName();
        this.uri = event.getUri();
        this.perms = event.getPerms();
        this.type = event.getType();
        this.icon = event.getIcon();
        this.orderNum = event.getOrderNum();
    }

    @EventSourcingHandler
    public void on(MenuDeleteEvent event){
        this.id = event.getId();
        if (event.getLogic()){
           this.deleted = true;
        } else {
            // TODO: 2018/2/20 delete
        }
    }


}
