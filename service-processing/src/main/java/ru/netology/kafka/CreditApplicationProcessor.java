package ru.netology.kafka;

import org.springframework.stereotype.Service;
import ru.netology.model.CreditApplication;
import ru.netology.service.CreditApplicationChecker;

@Service
// класс для обработки заявок
public class CreditApplicationProcessor {
    private final CreditApplicationChecker checker;  // внедряю класс для проверки
    private final CreditApplicationSender sender; // внедряею класс для отправки результата

    public CreditApplicationProcessor(CreditApplicationChecker checker, CreditApplicationSender sender) {
        this.checker = checker;
        this.sender = sender;
    }

    // метод принимает строку с данными заявки и обрабатывает её
    public void process(CreditApplication application) {

        // метод проверки заявки
        boolean isApproved = checker.check(application);

        // отправка результата через RabbitMQ
        sender.sendResult(isApproved, application.getId());
    }
}
