package ru.netology.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.netology.model.CreditApplicationEvent;

// Spring Bean, чтобы он автоматически создавался и управлялся Spring
@Component
@RequiredArgsConstructor
// класс слушает Kafka (получает сообщения, но не обрабатывает их)
public class CreditApplicationListener {

    // сервис для обработки запросов
    private final CreditApplicationProcessor processor;

    // слушает Kafka (указываю какой топик, какой сервис будет слушать)
    @KafkaListener(topics = "credit-applications", groupId = "credit-group")

    // метод для обработки заявки
    public void onMessage(CreditApplicationEvent event) {
        try {
            // отправка заявки на обработку
            processor.process(event);
        } catch (Exception e) {
            e.printStackTrace();
            // логирование
            System.err.
                    println("Ошибка при обработке Kafka-сообщения: " + e.getMessage());
        }
    }
}