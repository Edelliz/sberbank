package sberbank.loanservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAmountUpdateDto {
    private Long accountId;
    private Double amount;
}
