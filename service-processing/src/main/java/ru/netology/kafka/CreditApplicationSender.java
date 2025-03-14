package ru.netology.kafka;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

// класс для отправки результата
public class CreditApplicationSender {
    // объект для взаимодействия с RabbitMQ
    private final RabbitTemplate rabbit;

    public CreditApplicationSender(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    // метод для отправки результата через RabbitMQ
    public void sendResult(boolean isApproved, Long id) {
        // сообщение о результате
        String result = isApproved ? "ваша заявка" + id + "одобрена" : "ваша заявка" + id + "отклонена";

        // отправка строки
        rabbit.convertAndSend(result);
    }
}