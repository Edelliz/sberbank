package sberbank.loanservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Кредит
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan")
public class LoanEntity extends BaseEntity {
    /**
     * Срок кредита(в мес.)
     */
    @Column(name = "loan_period")
    private Integer loanPeriod;

    /**
     * Сумма кредита
     */
    @Column(name = "amount")
    private Double loanAmount;

    /**
     * Счет списания
     */
    @Column(name = "account_debiting_id")
    private Long accountDebitingId;

    /**
     * Счет пополнения
     */
    @Column(name = "account_replenishment_id")
    private Long accountReplenishmentId;

    /**
     * Тариф
     */
    @Column(name = "rate_id")
    private Long rateId;

    /**
     * Платеж
     */
    @Column(name = "payment")
    private Double payment;

    /**
     * Клиент - заемщик
     */
    @Column(name = "client_id")
    private Long clientId;

    /**
     * Долг
     */
    @Column(name = "debt")
    private Double debt;
}
