package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.model.CreateApplicationRequest;
import ru.netology.model.CreditApplication;
import ru.netology.repository.CreditApplicationRepository;

@Service
// класс при помощи CreditApplication преобразовавает CreateApplicationRequest в сущность и сохраняет в базу
public class CreditService {
    private final CreditApplicationRepository repository;

    public CreditService(CreditApplicationRepository repository) {
        this.repository = repository;
    }

    // метод для обработки заявки
    public String processApplication(CreateApplicationRequest request) {
        // преобразует запрос в сущность
        CreditApplication application = new CreditApplication(request);
        // сохраняем заявку в базу
        repository.save(application);
        return "заявка сохранена в базу с Id" + application.getId();
    }

    // метод для получения статуса заявки по ID
    public String getStatus(Long id) {
        //находит заявку по ID
        CreditApplication application = repository.findById(id).orElse(null);
        if (application == null) {
            // если заявка не найдена
            return "Заявка не найдена";
        }
        return "Статус заявки" + application.getStatus();
    }
}
