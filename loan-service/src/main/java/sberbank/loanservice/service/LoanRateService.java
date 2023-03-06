package sberbank.loanservice.service;

import sberbank.loanservice.domain.dto.LoanRateCreateDto;
import sberbank.loanservice.domain.dto.LoanRateDto;

import java.util.List;

/**
 * Интерфейс для работы с тарифами кредитов
 */
public interface LoanRateService {
    /**
     * Создание тарифа
     */
    public LoanRateDto createLoanRate(LoanRateCreateDto dto);

    /**
     * Получение всех тарифов
     */
    public List<LoanRateDto> getLoanRates();

    /**
     * Получение тарифа
     */
    public LoanRateDto getLoanRate(Long loanRateId);

    /**
     * Удаление тарифа
     */
    public void deleteLoanRate(Long loanRateId);
}
