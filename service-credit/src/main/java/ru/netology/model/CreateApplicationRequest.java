package ru.netology.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// класс для упаковки информации полученной из запроса
public class CreateApplicationRequest {
    BigDecimal loanAmount;       // сумма кредита
    int loanTerm;                // срок кредита
    BigDecimal userIncome;       // доход пользователя
    BigDecimal currentDebtLoad;  // текущая кредитная нагрузка
    int creditRating;            // текущий кредитный рейтинг
}