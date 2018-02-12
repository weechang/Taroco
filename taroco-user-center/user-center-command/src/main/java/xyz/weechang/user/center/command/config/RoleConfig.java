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
import xyz.weechang.user.center.command.aggregate.Role;

/**
 * @author weechang
 */
@Configuration
public class RoleConfig {

    @Autowired
    private EventStore eventStore;

    @Bean
    @Scope("prototype")
    public Role roleAggregate(){
        return new Role();
    }

    @Bean
    public AggregateFactory<Role> roleAggregateFactory(){
        SpringPrototypeAggregateFactory<Role> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("role");
        return aggregateFactory;
    }

    @Bean
    public Repository<Role> orderAggregateRepository(){
        EventSourcingRepository<Role> repository = new EventSourcingRepository<>(
                roleAggregateFactory(),
                eventStore
        );
        return repository;
    }
}
