package ru.netology.kafka;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.netology.model.CreditApplicationResponse;

@Component
// класс для отправки результата через RabbitMQ
public class CreditApplicationSender {
    // объект для взаимодействия с RabbitMQ
    private final RabbitTemplate rabbit;

    public CreditApplicationSender(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    // получает exchange из конфигураци (application.properties)
    @Value("${rabbitmq.exchange}")
    // присваивает его переменной
    private String exchange;

    // получает ключ из конфигураци (application.properties)
    @Value("${rabbitmq.routing-key}")
    // присваивает его переменной
    private String routingKey;

    @PostConstruct
    public void init() {
        System.out.println("Exchange: " + exchange);
        System.out.println("RoutingKey: " + routingKey);
    }

    // метод для отправки результата через RabbitMQ
    public void sendResult(boolean isApproved, Long id) {

        // объект для упаковки ответа
        var response = new CreditApplicationResponse(id, isApproved);

        // отправка строки в RabbitMQ
        rabbit.convertAndSend(exchange, routingKey, response);
    }
}