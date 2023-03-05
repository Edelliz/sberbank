package com.example.clientservice.controller;

import com.example.clientservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    /**
     * Открытие счета
     */
    @PostMapping("/{clientId}")
    public String createAccount(@PathVariable Long clientId) {
        return accountService.createAccount(clientId);
    }

    /**
     * Закрытие счета
     */
    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
    }

    /**
     * Пополнение счета
     */
    @PostMapping("refill/{accountId}/{amount}")
    public Long replenishAccount(
            @PathVariable Long accountId,
            @PathVariable Long amount
    ) {
        return accountService.replenishAccount(accountId, amount);
    }

    /**
     * Снятие со счета
     */
    @PostMapping("withdrawal/{accountId}/{amount}")
    public Long withdrawAccount(
            @PathVariable Long accountId,
            @PathVariable Long amount
    ) {
        return accountService.withdrawAccount(accountId, amount);
    }
}
