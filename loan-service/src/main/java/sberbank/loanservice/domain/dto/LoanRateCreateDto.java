package sberbank.loanservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO создания тарифа кредита
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRateCreateDto {

    /**
     * Наименование тарифа
     */
    private String name;

    /**
     * Процентная ставка
     */
    private Integer rate;

    /**
     * Идентификатор сотрудника, создавшего тариф
     */
    private Long employeeId;
}
