package com.example.clientservice.dto;

import lombok.Data;

@Data
public class AccountAmountUpdateDto {

    private Long accountId;

    private Double amount;
}
