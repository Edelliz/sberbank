package sberbank.loanservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.loanservice.domain.dto.LoanRateCreateDto;
import sberbank.loanservice.domain.dto.LoanRateDto;
import sberbank.loanservice.service.LoanRateService;

import java.util.List;

@RestController
@RequestMapping("/rate")
@RequiredArgsConstructor
public class LoanRateController {

    private final LoanRateService loanRateService;

    /**
     * Создание тарифа
     */
    @PostMapping
    public LoanRateDto createLoanRate(@RequestBody LoanRateCreateDto dto) {
        return loanRateService.createLoanRate(dto);
    }

    /**
     * Получение всех тарифов
     */
    @GetMapping
    public List<LoanRateDto> getLoanRates() {
        return loanRateService.getLoanRates();
    }

    /**
     * Получение тарифа
     */
    @GetMapping("/{loanRateId}")
    public LoanRateDto getLoanRate(@PathVariable Long loanRateId) {
        return loanRateService.getLoanRate(loanRateId);
    }

    /**
     * Удаление тарифа
     */
    @DeleteMapping("/{loanRateId}")
    public void deleteLoanRate(@PathVariable Long loanRateId) {
        loanRateService.deleteLoanRate(loanRateId);
    }
}
