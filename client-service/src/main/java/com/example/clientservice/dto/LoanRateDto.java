package com.example.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO тарифа кредита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRateDto {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Наименование тарифа
     */
    private String name;

    /**
     * Процентная ставка тарифа
     */
    private Integer rate;

    /**
     * Сотрудник - создатель
     */
    private UserDto employee;
}
