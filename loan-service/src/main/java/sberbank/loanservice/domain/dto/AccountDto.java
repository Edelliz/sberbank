package sberbank.loanservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Номер счета
     */
    private String number;

    /**
     * ФИО владельца
     */
    private String ownerFullName;

    /**
     * Сумма
     */
    private Long amount;

}
