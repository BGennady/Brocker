package ru.netology.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// класс для упаковки информации полученной из запроса
public class CreateApplicationRequest {
    String loanAmount;       // сумма кредита
    String loanTerm;         // срок кредита
    String userIncome;       // доход пользователя
    String currentDebtLoad;  // текущая кредитная нагрузка
    String creditRating;     // текущий кредитный рейтинг
}