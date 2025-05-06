package ru.netology.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RabbitMQconfig {

    @Configuration // обозначает конфигурационный класс Spring
    public class RabbitMQConfig {

        @Bean // создает очередь с именем "myQueue", параметр true — очередь сохраняется после перезапуска брокера
        public Queue myQueue() {
            return new Queue("myQueue", true); // /имя очереди, durable = true
        }

        @Bean // /создает обменник типа Direct с именем "myExchange"
        public DirectExchange exchange() {
            return new DirectExchange("myExchange");
        }

        @Bean // /связывает очередь "myQueue" с обменником "myExchange" по ключу "myRoutingKey"
        public Binding binding(Queue myQueue, DirectExchange exchange) {
            return BindingBuilder.bind(myQueue).to(exchange).with("myRoutingKey");
        }
    }
}
