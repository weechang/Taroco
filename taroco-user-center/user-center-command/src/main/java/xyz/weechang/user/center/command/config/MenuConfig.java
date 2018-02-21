package xyz.weechang.user.center.command.config;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import xyz.weechang.user.center.command.aggregate.Menu;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/12 10:00.
 */
@Configuration
public class MenuConfig {

    @Autowired
    private EventStore eventStore;

    @Bean
    @Scope("prototype")
    public Menu menuAggregate(){
        return new Menu();
    }

    @Bean
    public AggregateFactory<Menu> menuAggregateFactory(){
        SpringPrototypeAggregateFactory<Menu> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("menu");
        return aggregateFactory;
    }

    @Bean
    public Repository<Menu> menuAggregateRepository(){
        EventSourcingRepository<Menu> repository = new EventSourcingRepository<>(
                menuAggregateFactory(),
                eventStore
        );
        return repository;
    }
}
