package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.command.command.DeleteCommand;
import xyz.weechang.user.center.command.aggregate.User;
import xyz.weechang.user.center.command.command.UserCreateCommand;
import xyz.weechang.user.center.command.command.UserUpdateCommand;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:58.
 */
@Slf4j
@Component
public class UserCommandHandler {

    @Autowired
    private Repository<User> userAggregateRepository;

    @Autowired
    @Qualifier("eventBus")
    private EventBus eventBus;

    @CommandHandler
    public void handle(UserCreateCommand command) throws Exception {
        userAggregateRepository.newInstance(() -> {
            return new User(command);
        });
    }

    @CommandHandler
    public void handle(UserUpdateCommand command) {
        Aggregate<User> user = userAggregateRepository.load(command.getId());
        user.execute(aggregateRoot -> {
            aggregateRoot.update(command);
        });
    }

    @CommandHandler
    public void handle(DeleteCommand command) {
        Aggregate<User> user = userAggregateRepository.load(command.getId());
        user.execute(aggregateRoot -> {
            aggregateRoot.delete(command);
        });
    }
}
