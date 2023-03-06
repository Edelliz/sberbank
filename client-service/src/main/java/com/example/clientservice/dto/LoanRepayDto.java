package com.example.clientservice.dto;

import lombok.Data;

/**
 * DTO погашения кредита
 */
@Data
public class LoanRepayDto {
    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Размер платежа
     */
    private Double amount;

    /**
     * Идентификатор счета списания
     */
    private Long accountId;
}
