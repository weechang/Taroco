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
import xyz.weechang.user.center.command.aggregate.User;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/12 10:01.
 */
@Configuration
public class UserConfig {

    @Autowired
    private EventStore eventStore;

    @Bean
    @Scope("prototype")
    public User userAggregate(){
        return new User();
    }

    @Bean
    public AggregateFactory<User> userAggregateFactory(){
        SpringPrototypeAggregateFactory<User> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("user");
        return aggregateFactory;
    }

    @Bean
    public Repository<User> aggregateRepository(){
        EventSourcingRepository<User> repository = new EventSourcingRepository<>(
                userAggregateFactory(),
                eventStore
        );
        return repository;
    }
}
