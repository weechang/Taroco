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
import xyz.weechang.user.center.command.aggregate.Org;

/**
 * @author weechang 
 */
@Configuration
public class OrgConfig {

    @Autowired
    private EventStore eventStore;

    @Bean
    @Scope("prototype")
    public Org orgAggregate(){
        return new Org();
    }

    @Bean
    public AggregateFactory<Org> orgAggregateFactory(){
        SpringPrototypeAggregateFactory<Org> aggregateFactory = new SpringPrototypeAggregateFactory<>();
        aggregateFactory.setPrototypeBeanName("org");
        return aggregateFactory;
    }

    @Bean
    public Repository<Org> orgAggregateRepository(){
        EventSourcingRepository<Org> repository = new EventSourcingRepository<>(
                orgAggregateFactory(),
                eventStore
        );
        return repository;
    }
}
