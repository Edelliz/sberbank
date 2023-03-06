package sberbank.loanservice.domain.dto;

import lombok.Data;

/**
 * DTO для создания кредита
 */
@Data
public class LoanCreateDto {

    /**
     * Срок кредита(в мес.)
     */
    private Integer loanPeriod;

    /**
     * Сумма кредита
     */
    private Double loanAmount;

    /**
     * Счет списания
     */
    private Long accountDebitingId;

    /**
     * Счет пополнения
     */
    private Long accountReplenishmentId;

    /**
     * Тариф
     */
    private Long rateId;

    /**
     * Клиент - заемщик
     */
    private Long clientId;
}
