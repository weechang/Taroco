package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import xyz.weechang.user.center.command.aggregate.Menu;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:57.
 */
@Slf4j
public class MenuCommandHandler {

    private Repository<Menu> menuRepository;
    private EventBus eventBus;

    public MenuCommandHandler(Repository<Menu> repository, EventBus eventBus) {
        this.menuRepository = repository;
        this.eventBus = eventBus;
    }
}
