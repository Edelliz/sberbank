package sberbank.coreservice.domain.entity.dto;

import lombok.Data;

@Data
public class AccountAmountUpdateDto {
    private Long accountId;
    private Double amount;
}
