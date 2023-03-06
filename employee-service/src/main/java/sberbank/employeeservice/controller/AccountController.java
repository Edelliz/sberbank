package sberbank.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.employeeservice.domain.dto.ClientsAccounts;
import sberbank.employeeservice.domain.dto.HistoryOperation;
import sberbank.employeeservice.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/{userId}")
    public ClientsAccounts getUsersAccountsList(@PathVariable int userId) {
        return accountService.getUsersAccountsList(userId);
    }
    @GetMapping("/history/{accountId}")
    public List<HistoryOperation> getAccountHistory(@PathVariable int accountId) {
        return accountService.getAccountHistory(accountId);
    }
}
