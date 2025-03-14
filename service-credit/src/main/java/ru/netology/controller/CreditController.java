package ru.netology.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.model.CreateApplicationRequest;
import ru.netology.service.CreditService;

@RestController // аннотация для обработки HTTP-запросов
@RequestMapping("/api/credit") // // базовый путь для всех эндпоинтов
// класс принимающий запрос
public class CreditController {

    // сервис для обработки запросов
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    // эндпоинт для подачи заявки
    @PostMapping("/apply")
    public String applyForCredit(@RequestBody CreateApplicationRequest request) {
        return creditService.processApplication(request);
    }

    // эндпоинт для получения статуса заявки по id
 @GetMapping("/status{id}")
    public String getApplicationStatus (@PathVariable Long id){
        return creditService.getStatus(id);
 }
}
