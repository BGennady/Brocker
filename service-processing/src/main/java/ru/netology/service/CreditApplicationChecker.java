package ru.netology.service;

import org.springframework.stereotype.Component;
import ru.netology.model.CreditApplicationEvent;

@Component
// класс для проверки заявки
public class CreditApplicationChecker {

    // метод для проверки заявки
    public boolean check(CreditApplicationEvent event) throws Exception {

        // расчет допустимого ежемесячного платежа
        double maxMonthlyPayment = event.getUserIncome().doubleValue() * 0.5;

        // расчет ежемесячного фактического ежемесячного платежа
        double monthlyPayment = event.getLoanAmount().doubleValue() / (double) event.getLoanTerm();

        // проверка на соответсвие условиям одобрения кредита по ежемесячному платежу
        if (monthlyPayment + event.getCurrentDebtLoad().doubleValue() > maxMonthlyPayment) {
            System.out.println("Сумма ежемесячного платежа превышает максимальный.");
            return false;
        }
        // проверка на соответсвие условиям одобрения кредита по кредитному рейтингу
        if (event.getCreditRating() < 55) {
            System.out.println("Низкий кредитный рейтинг.");
            return false;
        }
        // заявка одобрена
        return true;
    }
}