package ru.netology.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.model.CreditApplication;
import ru.netology.service.CreditApplicationChecker;

@Service
@RequiredArgsConstructor
// класс для обработки заявок
public class CreditApplicationProcessor {
    private final CreditApplicationChecker checker;  // класс для проверки
    private final CreditApplicationSender sender; // класс для отправки результата

    // метод для обработки заявки и отправки ответа
    public void process(CreditApplication application) throws Exception {

        // метод проверки заявки
        boolean isApproved = checker.check(application);

        // отправка результата через RabbitMQ
        sender.sendResult(isApproved, application.getId());
    }
}
