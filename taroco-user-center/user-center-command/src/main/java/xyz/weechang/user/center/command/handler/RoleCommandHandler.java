package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import xyz.weechang.user.center.command.aggregate.Role;
import xyz.weechang.user.center.command.command.RoleCreateCommand;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:31.
 */
@Slf4j
public class RoleCommandHandler {

    private EventBus eventBus;
    private Repository<Role> repository;

    public RoleCommandHandler(Repository<Role> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @CommandHandler
    public void handle(RoleCreateCommand command) throws Exception {
        repository.newInstance(() -> {
            return new Role(command);
        });
    }
}
