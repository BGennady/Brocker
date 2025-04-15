package ru.netology.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration // класс конфигурационный — Spring будет создавать бины
public class RabbitMQConfig {

    @Bean // очередь с именем "myQueue", durable=true — сохраняется после перезапуска
    public Queue myQueue() {
        return new Queue("myQueue", true);
    }

    @Bean // прямой (direct) обменник с именем "myExchange"
    public DirectExchange exchange() {
        return new DirectExchange("myExchange");
    }

    @Bean // привязывает очередь к обменнику с routing key "myRoutingKey"
    public Binding binding(Queue myQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(myQueue) // очередь
                .to(exchange)  // обменник
                .with("myRoutingKey"); // routing key
    }

    @Bean // конвертер, превращает объекты в JSON при отправке в RabbitMQ
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean  // создает RabbitTemplate для отправки JSON
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        // создаём шаблон для отправки сообщений в RabbitMQ, используя подключение (ConnectionFactory)
        RabbitTemplate template = new RabbitTemplate(connectionFactory);

        // устанавливаем конвертер сообщений: превращает объекты Java в JSON и обратно
        template.setMessageConverter(messageConverter);

        // возвращаем готовый бин, который будет использоваться для отправки сообщений
        return template;
    }

}
