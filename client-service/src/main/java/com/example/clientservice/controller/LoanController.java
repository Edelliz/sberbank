package com.example.clientservice.controller;

import com.example.clientservice.dto.LoanCreateDto;
import com.example.clientservice.dto.LoanDto;
import com.example.clientservice.dto.LoanRepayDto;
import com.example.clientservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    /**
     * Создание кредита
     */
    @PostMapping
    public LoanDto createLoan(@RequestBody LoanCreateDto dto) {
        return loanService.createLoan(dto);
    }

    /**
     * Получение кредита
     */
    @GetMapping("/{loanId}")
    public LoanDto getLoan(@PathVariable Long loanId) {
        return loanService.getLoan(loanId);
    }

    /**
     * Получение кредитов пользователя
     */
    @GetMapping("/{clientId}/client")
    public List<LoanDto> getLoans(@PathVariable Long clientId) {
        return loanService.getLoans(clientId);
    }

    /**
     * Погашение кредита
     */
    @PostMapping("/repay")
    public LoanDto repayLoan(@RequestBody LoanRepayDto dto) {
        return loanService.repayLoan(dto);
    }
}
