package ru.netology.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.netology.model.ApplicationStatus;
import ru.netology.model.CreditApplication;
import ru.netology.model.CreditApplicationResponse;
import ru.netology.repository.CreditApplicationRepository;

@Service
@AllArgsConstructor
// класс слушатель RabbitMQ
public class CreditApplicationListener {
    private final CreditApplicationRepository repository;


    // метод для прослушивания ответов от RabbitMQ и обновления статуса заявки
    @RabbitListener(queues = "myQueue") // очередь, из которой слушает

    public void updateStatus(CreditApplicationResponse response) {
        try {
            // извлекает ID и результат из объекта ответа
            Long applicationId = response.getId();
            Boolean isApproved = response.getResult();

            // получает заявку по ID из базы данных
            CreditApplication application = repository.findById(applicationId)
                    .orElseThrow(() -> new RuntimeException("Заявка ID " + applicationId + " не найдена"));

            // обновляет статус заявки
            ApplicationStatus status = isApproved ? ApplicationStatus.APPROVED : ApplicationStatus.REJECTED;
            application.setStatus(status);

            // сохраняет заявку в базе данных
            repository.save(application);

            //логирует обновление
            System.out.printf("Заявка с %d обновлена. Новый статус: %s%n", applicationId, status);
        } catch (Exception e) {
            // Логирование ошибки
            System.err.println("Ошибка при обработке RabbitMQ-сообщения: " + e.getMessage());
        }
    }
}