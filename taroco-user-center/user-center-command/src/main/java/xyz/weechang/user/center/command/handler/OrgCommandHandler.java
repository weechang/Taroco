package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import xyz.weechang.taroco.core.command.DeleteCommand;
import xyz.weechang.user.center.command.aggregate.Org;
import xyz.weechang.user.center.command.command.OrgCreateCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 17:32.
 */
@Slf4j
public class OrgCommandHandler {

    private Repository<Org> repository;
    private EventBus eventBus;

    public OrgCommandHandler(Repository<Org> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @CommandHandler
    public void handle(OrgCreateCommand command) throws Exception {
        repository.newInstance(() -> {
            return new Org(command);
        });
    }

    @CommandHandler
    public void handle(OrgUpdateCommand command) {
        Aggregate<Org> org = repository.load(command.getId());
        org.execute(aggregateRoot -> {
            aggregateRoot.update(command);
        });
    }

    @CommandHandler
    public void handle(DeleteCommand command) {
        Aggregate<Org> org = repository.load(command.getId());
        org.execute(aggregateRoot -> {
            aggregateRoot.delete(command);
        });
    }
}
