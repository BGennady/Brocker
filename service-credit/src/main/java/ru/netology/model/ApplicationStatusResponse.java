package ru.netology.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
// класс для формирования ответа по статусу (или скажи как корректнее навписать)
public class ApplicationStatusResponse {
   private  Long id;
   private String status;
}