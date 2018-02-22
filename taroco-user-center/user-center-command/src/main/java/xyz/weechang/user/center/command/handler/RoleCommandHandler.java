package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import xyz.weechang.user.center.command.aggregate.Role;
import xyz.weechang.user.center.command.command.RoleCreateCommand;
import xyz.weechang.user.center.command.command.RoleDeleteCommand;
import xyz.weechang.user.center.command.command.RoleUpdateCommand;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:31.
 */
@Slf4j
@Component
public class RoleCommandHandler {

    @Autowired
    private Repository<Role> roleAggregateRepository;

    @Autowired
    @Qualifier("eventBus")
    private EventBus eventBus;

    @CommandHandler
    public void handle(RoleCreateCommand command) throws Exception {
        roleAggregateRepository.newInstance(() -> {
            return new Role(command);
        });
    }

    @CommandHandler
    public void handle(RoleUpdateCommand command) {
        Aggregate<Role> role = roleAggregateRepository.load(command.getId());
        role.execute(aggregateRoot -> {
            aggregateRoot.update(command);
        });
    }

    @CommandHandler
    public void handle(RoleDeleteCommand command) {
        Aggregate<Role> role = roleAggregateRepository.load(command.getId());
        role.execute(aggregateRoot -> {
            aggregateRoot.delete(command);
        });
    }
}
