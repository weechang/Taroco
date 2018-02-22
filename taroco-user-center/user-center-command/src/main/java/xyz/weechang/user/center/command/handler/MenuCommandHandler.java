package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import xyz.weechang.user.center.command.aggregate.Menu;
import xyz.weechang.user.center.command.command.MenuCreateCommand;
import xyz.weechang.user.center.command.command.MenuDeleteCommand;
import xyz.weechang.user.center.command.command.MenuUpdateCommand;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:57.
 */
@Slf4j
@Component
public class MenuCommandHandler {

    @Autowired
    private Repository<Menu> menuAggregateRepository;

    @Autowired
    @Qualifier("eventBus")
    private EventBus eventBus;

    @CommandHandler
    public void on(MenuCreateCommand command) throws Exception {
        menuAggregateRepository.newInstance(()->{
            return new Menu(command);
        });
    }

    @CommandHandler
    public void on(MenuUpdateCommand command){
        Aggregate<Menu> menu = menuAggregateRepository.load(command.getId());
        menu.execute(aggregateRoot -> {
            aggregateRoot.update(command);
        });
    }

    @CommandHandler
    public void on(MenuDeleteCommand command){
        Aggregate<Menu> menu = menuAggregateRepository.load(command.getId());
        if (command.getLogic()){
            menu.execute(aggregateRoot -> {
                aggregateRoot.delete(command);
            });
        } else {
            // TODO: 2018/2/20 deleted
//            menu.isDeleted();
        }
    }
}
