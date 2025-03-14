package ru.netology.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long id;
    private BigDecimal loanAmount; // сумма кредита
    private int loanTerm;          // срок кредита
    private BigDecimal userIncome;       // доход пользователя
    private BigDecimal currentDebtLoad;  // текущая кредитная нагрузка
    private BigDecimal creditRating;     // текущий кредитный рейтинг
    private String status = "в обработке"; //базовый статус


    // конструктор для создания сущности из CreateApplicationRequest
    public CreditApplication(CreateApplicationRequest request) {
        this.loanAmount = new BigDecimal(request.getLoanAmount());
        this.loanTerm = Integer.parseInt(request.getLoanTerm());
        this.userIncome = new BigDecimal(request.getUserIncome());
        this.currentDebtLoad = new BigDecimal(request.getCurrentDebtLoad());
        this.creditRating = new BigDecimal(request.getCreditRating());
    }
}
