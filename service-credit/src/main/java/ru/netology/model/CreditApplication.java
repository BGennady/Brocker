package ru.netology.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
// класс преобразовывает данные из CreateApplicationRequest в сущность
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // id
    private BigDecimal loanAmount;      // сумма кредита
    private int loanTerm;               // срок кредита
    private BigDecimal userIncome;      // доход пользователя
    private BigDecimal currentDebtLoad; // текущая кредитная нагрузка
    private int creditRating;           // текущий кредитный рейтинг
    // анотация указывает что в бд сохраняется как строка
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.IN_PROCESS; //базовый статус


    // конструктор для создания сущности из CreateApplicationRequest
    public CreditApplication(CreateApplicationRequest request) {
        this.loanAmount = request.getLoanAmount();
        this.loanTerm = request.getLoanTerm();
        this.userIncome = request.getUserIncome();
        this.currentDebtLoad = request.getCurrentDebtLoad();
        this.creditRating = request.getCreditRating();
    }
}
