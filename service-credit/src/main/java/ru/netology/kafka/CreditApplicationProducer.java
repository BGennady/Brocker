package ru.netology.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.netology.model.CreditApplicationEvent;

// отправка заявки в Kafka
@Service
public class CreditApplicationProducer {

    private final KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate;

    public CreditApplicationProducer(KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // метод для отправки заявки через Kafka
    public void sendApplication(CreditApplicationEvent applicationEvent) {
        kafkaTemplate.send("credit-application-topic", applicationEvent); // отправляет событие в Kafka
    }
}
