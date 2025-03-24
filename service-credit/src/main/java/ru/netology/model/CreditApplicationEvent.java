package ru.netology.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
// класс обёртка (DTO)
public class CreditApplicationEvent {
    private Long id;
    private BigDecimal loanAmount;
    private int loanTerm;
    private BigDecimal userIncome;
    private BigDecimal currentDebtLoad;
    private int creditRating;
}
