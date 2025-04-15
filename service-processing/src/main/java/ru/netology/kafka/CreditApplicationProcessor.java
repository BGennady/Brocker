package ru.netology.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.model.CreditApplicationEvent;
import ru.netology.service.CreditApplicationChecker;

@Service
@RequiredArgsConstructor
// класс для обработки заявок
public class CreditApplicationProcessor {
    private final CreditApplicationChecker checker;  // класс для проверки
    private final CreditApplicationSender sender; // класс для отправки результата

    // метод для обработки заявки и отправки ответа
    public void process(CreditApplicationEvent event) throws Exception {
        try {
            // метод проверки заявки
            boolean isApproved = checker.check(event);

            // отправка результата через RabbitMQ
            sender.sendResult(isApproved, event.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
