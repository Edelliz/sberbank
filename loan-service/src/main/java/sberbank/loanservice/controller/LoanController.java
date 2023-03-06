package sberbank.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.loanservice.domain.dto.LoanCreateDto;
import sberbank.loanservice.domain.dto.LoanDto;
import sberbank.loanservice.domain.dto.LoanRepayDto;
import sberbank.loanservice.service.LoanService;

@RestController
@RequestMapping()
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
     * Погашение кредита
     */
    @PostMapping("/repay")
    public LoanDto repayLoan(@RequestBody LoanRepayDto dto) {
        return loanService.repayLoan(dto);
    }
}
