package ru.netology.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("myExchange");
    }

    @Bean
    public Binding binding(Queue myQueue, DirectExchange exchange) {
        return BindingBuilder.bind(myQueue).to(exchange).with("myRoutingKey");
    }
}