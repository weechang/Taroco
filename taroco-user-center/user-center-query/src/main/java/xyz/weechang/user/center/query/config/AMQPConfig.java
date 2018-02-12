package xyz.weechang.user.center.query.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @author weechang
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

    @Bean
    public SpringAMQPMessageSource orgQueueMessageSource(Serializer serializer){
        return new SpringAMQPMessageSource(serializer){
            @RabbitListener(queues = "org")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.debug("Org message received: "+message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    @Bean
    public SpringAMQPMessageSource menuQueueMessageSource(Serializer serializer){
        return new SpringAMQPMessageSource(serializer){
            @RabbitListener(queues = "menu")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.debug("Menu message received: "+message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    @Bean
    public SpringAMQPMessageSource roleQueueMessageSource(Serializer serializer){
        return new SpringAMQPMessageSource(serializer){
            @RabbitListener(queues = "role")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.debug("Role message received: "+message.toString());
                super.onMessage(message, channel);
            }
        };
    }

    @Bean
    public SpringAMQPMessageSource userQueueMessageSource(Serializer serializer){
        return new SpringAMQPMessageSource(serializer){
            @RabbitListener(queues = "user")
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                log.debug("User message received: "+message.toString());
                super.onMessage(message, channel);
            }
        };
    }
}
