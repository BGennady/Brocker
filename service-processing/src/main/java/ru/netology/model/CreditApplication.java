package ru.netology.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity // класс является сущностью для базы данных
@Getter  // Lombok создаёт геттеры автоматически
@Setter  // Lombok создаёт сеттеры автоматически
@NoArgsConstructor // Lombok создаёт конструктор без аргументов

public class CreditApplication {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id; // id заявки
    private BigDecimal loanAmount; // сумма кредита
    private int loanTerm;          // срок кредита в месяцах
    private BigDecimal userIncome; // доход пользователя
    private BigDecimal currentDebtLoad; // текущая кредитная нагрузка
    private double creditRating; // кредитный рейтинг пользователя
    private String status = "в обработке"; // статус заявки (по умолчанию "в обработке")

    public CreditApplication(BigDecimal loanAmount, int loanTerm, BigDecimal userIncome, BigDecimal currentDebtLoad, double creditRating, String status) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.userIncome = userIncome;
        this.currentDebtLoad = currentDebtLoad;
        this.creditRating = creditRating;
        this.status = status;
    }
}