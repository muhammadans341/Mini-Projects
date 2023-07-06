package org.microservices.customer.config;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Data
public class CustomerConfig {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;
    @Value("${rabbitmq.queue.notification}")
    private String queue;
    @Value("${rabbitmq.routing-key.notification-routing-key}")
    private String notificationRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.internalExchange);
    }
    @Bean
    public Queue notificationQueue(){
        return new Queue(this.queue);
    }
    @Bean
    public Binding internalTopicToNotificationBinding(){
        return BindingBuilder
                 .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(notificationRoutingKey);
    }
}
