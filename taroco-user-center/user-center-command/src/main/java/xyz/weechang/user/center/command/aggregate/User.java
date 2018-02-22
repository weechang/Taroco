package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.taroco.core.command.aggregate.BaseAggregate;
import xyz.weechang.user.center.command.command.UserCreateCommand;
import xyz.weechang.user.center.command.command.UserDeleteCommand;
import xyz.weechang.user.center.command.command.UserUpdateCommand;
import xyz.weechang.user.center.event.user.UserCreateEvent;
import xyz.weechang.user.center.event.user.UserDeleteEvent;
import xyz.weechang.user.center.event.user.UserUpdateEvent;

import java.util.List;

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
public class User extends BaseAggregate {

    private static final long serialVersionUID = -3990067819697040144L;
    @AggregateIdentifier
    private String id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private List<String> roles;

    private List<String> orgs;

    public User(UserCreateCommand command) {
        UserCreateEvent event = new UserCreateEvent(
                command.getId(), command.getAuditEntry(), command.getUsername(),
                command.getPassword(), command.getPhone(), command.getEmail(),
                command.getRoles(), command.getOrgs());
        apply(event);
    }

    public void update(UserUpdateCommand command) {
        UserUpdateEvent event = new UserUpdateEvent(
                command.getId(), command.getAuditEntry(), command.getPassword(),
                command.getPhone(), command.getEmail(), command.getRoles(), command.getOrgs());
        apply(event);
    }

    public void delete(UserDeleteCommand command) {
        UserDeleteEvent event =  new UserDeleteEvent(command.getId(), command.getAuditEntry(), command.getLogic());
        apply(event);
    }

    @EventSourcingHandler
    public void on(UserCreateEvent event) {
        this.id = event.getId();
        this.username = event.getUsername();
        this.password = event.getPassword();
        this.phone = event.getPhone();
        this.email = event.getEmail();
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent event) {
        this.id = event.getId();
        this.password = event.getPassword();
        this.phone = event.getPhone();
        this.email = event.getEmail();
    }

    @EventSourcingHandler
    public void on(UserDeleteCommand event) {
        this.id = event.getId();
        if (event.getLogic()){
            super.deleted = true;
        } else {
            // TODO: 2018/2/20 deleted
        }
    }
}
