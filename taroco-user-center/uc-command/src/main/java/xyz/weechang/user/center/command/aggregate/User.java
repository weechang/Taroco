package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.taroco.base.command.DeleteCommand;
import xyz.weechang.taroco.base.event.DeleteEvent;
import xyz.weechang.user.center.command.command.UserCreateCommand;
import xyz.weechang.user.center.command.command.UserUpdateCommand;
import xyz.weechang.user.center.common.event.UserCreateEvent;
import xyz.weechang.user.center.common.event.UserUpdateEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:51.
 */
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class User {

    @AggregateIdentifier
    private String id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Boolean enable;

    public User(UserCreateCommand command) {
        apply(new UserCreateEvent(
                command.getId(), command.getAuditEntry(), command.getUsername(),
                command.getPassword(), command.getPhone(), command.getEmail(),
                command.getEnable()));
    }

    public void update(UserUpdateCommand command) {
        apply(new UserUpdateEvent(
                command.getId(), command.getAuditEntry(), command.getPassword(),
                command.getPhone(), command.getEmail(), command.getEnable()));
    }

    public void delete(DeleteCommand command) {
        apply(new DeleteEvent(command.getId(), command.getAuditEntry(), command.getLogic()));
    }

    @EventSourcingHandler
    public void on(UserCreateEvent event) {
        this.id = event.getId();
        this.username = event.getUsername();
        this.password = event.getPassword();
        this.phone = event.getPhone();
        this.email = event.getEmail();
        this.enable = event.getEnable();
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent event) {
        this.id = event.getId();
        this.password = event.getPassword();
        this.phone = event.getPhone();
        this.email = event.getEmail();
        this.enable = event.getEnable();
    }

    @EventSourcingHandler
    public void on(DeleteCommand event) {
        this.id = event.getId();
    }
}
