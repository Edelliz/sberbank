package com.example.clientservice.controller;

import com.example.clientservice.dto.AccountAmountUpdateDto;
import com.example.clientservice.dto.AccountDto;
import com.example.clientservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     */
    @GetMapping("/{clientId}/client")
    public List<AccountDto> getUserAccounts(@PathVariable Long clientId) {

        return accountService.getUserAccounts(clientId);
    }

    /**
     * Получение счета
     */
    @GetMapping("/{accountId}")
    public AccountDto getAccount(@PathVariable Long accountId) {

        return accountService.getAccount(accountId);
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
    @PostMapping("refill")
    public AccountDto replenishAccount(
            @RequestBody AccountAmountUpdateDto dto
    ) {
        return accountService.replenishAccount(dto);
    }

    /**
     * Снятие со счета
     */
    @PostMapping("withdrawal")
    public AccountDto withdrawAccount(
            @RequestBody AccountAmountUpdateDto dto
    ) {
        return accountService.withdrawAccount(dto);
    }
}
