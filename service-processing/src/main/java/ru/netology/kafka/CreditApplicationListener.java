package ru.netology.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.netology.model.CreditApplication;

// Spring Bean, чтобы он автоматически создавался и управлялся Spring
@Component
// класс слушает Kafka (получает сообщения, но не обрабатывает их)
public class CreditApplicationListener {

    // сервис для обработки запросов
    private final CreditApplicationProcessor processor;

    // конструктор
    public CreditApplicationListener(CreditApplicationProcessor processor) {
        this.processor = processor;
    }

    // запускаю Kafka и указываю какой топик, какой сервис будет слушать
    @KafkaListener(topics = "credit_applications", groupId = "credit-group")

    // сообщение (JSON) преобразуется в объект CreditApplication
    public void onMessage(String message) {
        try {
            CreditApplication application = new ObjectMapper().readValue(message, CreditApplication.class);

            // отправка заявки на обработк
            processor.process(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}