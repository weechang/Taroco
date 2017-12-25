package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.weechang.taroco.base.command.DeleteCommand;
import xyz.weechang.taroco.core.exception.BusinessException;
import xyz.weechang.user.center.command.aggregate.User;
import xyz.weechang.user.center.command.command.UserCreateCommand;
import xyz.weechang.user.center.command.command.UserUpdateCommand;
import xyz.weechang.user.center.common.error.UCError;
import xyz.weechang.user.center.query.dao.UserDao;
import xyz.weechang.user.center.query.domain.UserEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:58.
 */
@Slf4j
public class UserCommandHandler {

    private Repository<User> repository;
    private EventBus eventBus;

    public UserCommandHandler(Repository<User> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Autowired
    private UserDao userDao;

    @CommandHandler
    public void handle(UserCreateCommand command) throws Exception {
        UserEntry saved = userDao.findByUsername(command.getUsername());
        if (saved != null) {
            throw new BusinessException(UCError.USERNAME_IS_EXIST);
        }
        repository.newInstance(() -> {
            return new User(command);
        });
    }

    @CommandHandler
    public void handle(UserUpdateCommand command) {
        Aggregate<User> user = repository.load(command.getId());
        user.execute(aggregateRoot -> {
            aggregateRoot.update(command);
        });
    }

    @CommandHandler
    public void handle(DeleteCommand command) {
        Aggregate<User> user = repository.load(command.getId());
        user.execute(aggregateRoot -> {
            aggregateRoot.delete(command);
        });
    }
}
