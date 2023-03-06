package sberbank.loanservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * DTO кредита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Тариф
     */
    private LoanRateDto rate;

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
     * Долг
     */
    private Double debt;

}
