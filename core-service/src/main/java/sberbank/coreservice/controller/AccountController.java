package sberbank.coreservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.coreservice.domain.entity.dto.AccountDto;
import sberbank.coreservice.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    /**
     * Просмотр всех счетов
     */
    @GetMapping()
    public List<AccountDto> getAccounts() {

        return accountService.getAccounts();
    }

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
     * Снятие счета
     */
    @PostMapping("withdrawal/{accountId}/{amount}")
    public Long withdrawAccount(
            @PathVariable Long accountId,
            @PathVariable Long amount
    ) {

        return accountService.withdrawAccount(accountId, amount);
    }


}
