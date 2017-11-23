package xyz.weechang.apigateway.configuration;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * cloud 注册
 *
 * @author weechang
 */
public class CloudConfiguration extends AbstractCloudConfig {
    
    @Bean
    public DataSource dataSource() {
        return connectionFactory().dataSource();
    }
    
    @Profile("cloud")
    @Bean()
    public ConnectionFactory rabbitFactory() {
        return connectionFactory().rabbitConnectionFactory();
    }
}
