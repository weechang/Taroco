package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import xyz.weechang.user.center.command.aggregate.Token;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:57.
 */
@Slf4j
public class TokenCommandHandler {

    private Repository<Token> repository;
    private EventBus eventBus;

    public TokenCommandHandler(Repository<Token> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }
}
