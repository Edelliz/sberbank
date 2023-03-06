package sberbank.loanservice.service;

import sberbank.loanservice.domain.dto.LoanCreateDto;
import sberbank.loanservice.domain.dto.LoanDto;
import sberbank.loanservice.domain.dto.LoanRepayDto;

import java.util.List;

/**
 * Интерфейс для работы с кредитами
 */
public interface LoanService {
    /**
     * Создание кредита
     */
    public LoanDto createLoan(LoanCreateDto dto);

    /**
     * Получение кредит
     */
    public LoanDto getLoan(Long loanId);

    /**
     * Погашение кредита
     */
    public LoanDto repayLoan(LoanRepayDto dto);

    /**
     * Получение кредитов пользователя
     */
    public List<LoanDto> getLoans(Long clientId);
}
