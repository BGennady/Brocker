package ru.netology.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.model.ApplicationStatusResponse;
import ru.netology.model.CreateApplicationRequest;
import ru.netology.model.CreditApplication;
import ru.netology.model.CreditApplicationEvent;
import ru.netology.repository.CreditApplicationRepository;

@Service
@RequiredArgsConstructor
// класс при помощи CreditApplication преобразовавает CreateApplicationRequest в сущность и сохраняет в базу
public class CreditService {
    private final CreditApplicationRepository repository;
    private final KafkaProducerService producerService;

    // метод для обработки заявки
    public Long processApplication(CreateApplicationRequest request) {
        // преобразует запрос в сущность
        CreditApplication application = new CreditApplication(request);
        // сохраняем заявку в базу
        repository.save(application);
        return application.getId();
    }

    // метод для получения статуса заявки по ID
    public ApplicationStatusResponse getStatus(Long id) {
        // получение заявку из базы данных по ID
        CreditApplication application = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Заявка не найдена"));

        // отправка сообщения в Kafka
        CreditApplicationEvent event = new CreditApplicationEvent(
                application.getId(),              //id
                application.getLoanAmount(),      // сумма кредита
                application.getLoanTerm(),        // срок кредита
                application.getUserIncome(),      // доход пользователя
                application.getCurrentDebtLoad(), // текущая кредитная нагрузка
                application.getCreditRating()     // текущий кредитный рейтинг
        );
        producerService.sendApplication(event);

        // возврат объекта с ID и статусом
        return new ApplicationStatusResponse(application.getId(), application.getStatus().name());
    }
}

