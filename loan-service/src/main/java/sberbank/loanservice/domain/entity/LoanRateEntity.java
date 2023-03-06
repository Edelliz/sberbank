package sberbank.loanservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sberbank.loanservice.domain.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Тариф кредита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loan_rate")
public class LoanRateEntity extends BaseEntity {

    /**
     * Наименование тарифа
     */
    @Column(name = "name_rate")
    private String name;

    /**
     * Процентная ставка тарифа
     */
    @Column(name = "rate")
    private Integer rate;

    /**
     * Сотрудник - создатель
     */
    @Column(name = "employee_id")
    private Long employeeId;
}
