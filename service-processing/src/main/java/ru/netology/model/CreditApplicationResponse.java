package ru.netology.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// класс для упаковки ответа
public class CreditApplicationResponse {
    private Long id;
    private Boolean result;
}