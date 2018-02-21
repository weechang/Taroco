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
@Component
public class OrgCommandHandler {

    @Autowired
    private Repository<Org> orgAggregateRepository;

    @Autowired
    @Qualifier("eventBus")
    private EventBus eventBus;

    @CommandHandler
    public void handle(OrgCreateCommand command) throws Exception {
        orgAggregateRepository.newInstance(() -> {
            return new Org(command);
        });
    }

    @CommandHandler
    public void handle(OrgUpdateCommand command) {
        Aggregate<Org> org = orgAggregateRepository.load(command.getId());
        org.execute(aggregateRoot -> {
            aggregateRoot.update(command);
        });
    }

    @CommandHandler
    public void handle(DeleteCommand command) {
        Aggregate<Org> org = orgAggregateRepository.load(command.getId());
        org.execute(aggregateRoot -> {
            aggregateRoot.delete(command);
        });
    }
}
