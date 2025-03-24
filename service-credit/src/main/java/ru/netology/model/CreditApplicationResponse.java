package ru.netology.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CreditApplicationResponse {
    private Long id;
    private Boolean result;
}