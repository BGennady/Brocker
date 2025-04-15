package ru.netology.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// DTO для передачи данных кредитной заявки между сервисами
public class CreditApplicationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;                    // id
    private BigDecimal loanAmount;      // сумма кредита
    private int loanTerm;               // срок кредита
    private BigDecimal userIncome;      // доход пользователя
    private BigDecimal currentDebtLoad; // текущая кредитная нагрузка
    private int creditRating;           // текущий кредитный рейтинг
}