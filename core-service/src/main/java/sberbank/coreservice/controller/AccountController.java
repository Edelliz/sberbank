package sberbank.coreservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.coreservice.domain.entity.dto.AccountAmountUpdateDto;
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
     * Получение счетов клиента
     * */
    @GetMapping("/{clientId}/client")
    public List<AccountDto> getUserAccounts(@PathVariable Long clientId) {

        return accountService.getUserAccounts(clientId);
    }

    /**
     * Получение счета
     * */
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
    public AccountDto replenishAccount(@RequestBody AccountAmountUpdateDto dto) {

        return accountService.replenishAccount(dto);
    }

    /**
     * Снятие счета
     */
    @PostMapping("withdrawal")
    public AccountDto withdrawAccount(
            @RequestBody AccountAmountUpdateDto dto
    ) {

        return accountService.withdrawAccount(dto);
    }


}
