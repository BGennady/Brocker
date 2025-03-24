package ru.netology.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.netology.model.CreditApplicationEvent;


@Service
@RequiredArgsConstructor  // конструктор, принимающий все final-поля
// класс для отправки сообщений в kafka
public class KafkaProducerService {

    // аннотация для внедрения зависимостей
    private KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate;
    // название топика (канала передачи данных)
    private static final String TOPIC = "credit-applications";

    // метод отправляет сообщение в Kafka
    final void sendApplication(CreditApplicationEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}