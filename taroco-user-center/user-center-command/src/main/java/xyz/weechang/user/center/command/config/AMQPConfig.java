package xyz.weechang.user.center.command.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *
 * @author weechang
 *
 */
@Slf4j
@Configuration
public class AMQPConfig {

    @Value("${axon.amqp.exchange}")
    private String exchangeName;

    @Bean
    public Queue orgQueue(){
        return new Queue("org", false);
    }

    @Bean
    public Queue roleQueue(){
        return new Queue("role",false);
    }

    @Bean
    public Queue menuQueue(){
        return new Queue("menu",false);
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user",false);
    }

    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.topicExchange(exchangeName).durable(false).build();
    }

    @Bean
    public Binding orgQueueBinding() {
        return BindingBuilder.bind(orgQueue()).to(exchange()).with("#.org.#").noargs();
    }

    @Bean
    public Binding roleQueueBinding() {
        return BindingBuilder.bind(roleQueue()).to(exchange()).with("#.role.#").noargs();
    }

    @Bean
    public Binding menuQueueBinding() {
        return BindingBuilder.bind(menuQueue()).to(exchange()).with("#.menu.#").noargs();
    }

    @Bean
    public Binding userQueueBinding() {
        return BindingBuilder.bind(userQueue()).to(exchange()).with("#.user.#").noargs();
    }
}
