package ru.netology.service;

import org.springframework.stereotype.Component;
import ru.netology.model.CreditApplication;

@Component
// класс для проверки заявки
public class CreditApplicationChecker {

    // метод для проверки заявки
    public boolean check(CreditApplication application) throws Exception {

        // расчет допустимого ежемесячного платежа
        double maxMonthlyPayment = application.getUserIncome().doubleValue() * 0.5;

        // расчет ежемесячного фактического ежемесячного платежа
        double monthlyPayment = application.getLoanAmount().doubleValue() / (double) application.getLoanTerm();

        // проверка на соответсвие условиям одобрения кредита по ежемесячному платежу


        if (monthlyPayment + application.getCurrentDebtLoad().doubleValue() > maxMonthlyPayment) {
            throw new Exception("Сумма ежемесячного платежа превышает максимальный.");
            // проверка на соответсвие условиям одобрения кредита по кредитному рейтингу
        }
        if (application.getCreditRating() < 100) {
            throw new Exception("Низкий кредитный рейтинг.");
        }
        // заявка одобрена
        return true;
    }
}
