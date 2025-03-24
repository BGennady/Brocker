package ru.netology.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.ApplicationStatusResponse;
import ru.netology.model.CreateApplicationRequest;
import ru.netology.service.CreditService;

@RestController // аннотация для обработки HTTP-запросов
@RequestMapping("/api/credit") // базовый путь для всех эндпоинтов
@AllArgsConstructor
// класс принимающий запрос
public class CreditController {

    // сервис для обработки запросов
    private final CreditService creditService;

    // эндпоинт для подачи заявки
    @PostMapping("/apply")
    public ResponseEntity<Long> applyForCredit(@Valid @RequestBody CreateApplicationRequest request) {
        Long applicationId = creditService.processApplication(request);
        return ResponseEntity.ok(applicationId);
    }

    // эндпоинт для получения статуса заявки по id
    @GetMapping("/status/{id}")
    public ResponseEntity<ApplicationStatusResponse> getApplicationStatus(@PathVariable Long id) {
        ApplicationStatusResponse response = creditService.getStatus(id);
        return ResponseEntity.ok(response);
    }
}