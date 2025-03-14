package ru.netology.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.netology.model.CreditApplication;
import ru.netology.repository.CreditApplicationRepository;

@Service
// класс слушатель RabbitMQ
public class CreditApplicationListener {
    private final CreditApplicationRepository repository;

    public CreditApplicationListener(CreditApplicationRepository repository) {
        this.repository = repository;
    }

    // метод для прослушивания ответов от RabbitMQ и обновления статуса заявки
    @RabbitListener(queues = "credit-status-queue") // очередь, из которой слушает
    public void updateStatus(CreditApplication application) {
        // ищет заявку по ID
        CreditApplication existingApplication = repository.findById(application.getId()).orElse(null);
        if (existingApplication != null) {
            existingApplication.setStatus(application.getStatus()); // обновляет статус
            repository.save(existingApplication); // сохряняет обновлённый объект в базе
        }
    }
}
