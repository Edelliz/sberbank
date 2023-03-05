package com.example.clientservice.controller;

import com.example.clientservice.dto.AccountDto;
import com.example.clientservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * Получение счетов клиента
     * */
    @GetMapping("/{clientId}")
    public List<AccountDto> getAccount(@PathVariable Long clientId) {

        return accountService.getAccount(clientId);
    }

    /**
     * Открытие счета
     */
    @PostMapping("/{clientId}")
    public AccountDto createAccount(@PathVariable Long clientId) {
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
    public AccountDto replenishAccount(
            @PathVariable Long accountId,
            @PathVariable Long amount
    ) {
        return accountService.replenishAccount(accountId, amount);
    }

    /**
     * Снятие со счета
     */
    @PostMapping("withdrawal/{accountId}/{amount}")
    public AccountDto withdrawAccount(
            @PathVariable Long accountId,
            @PathVariable Long amount
    ) {
        return accountService.withdrawAccount(accountId, amount);
    }
}
