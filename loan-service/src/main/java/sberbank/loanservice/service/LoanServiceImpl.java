package sberbank.loanservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.loanservice.domain.dto.LoanCreateDto;
import sberbank.loanservice.domain.dto.LoanDto;
import sberbank.loanservice.domain.dto.LoanRateDto;
import sberbank.loanservice.domain.dto.LoanRepayDto;
import sberbank.loanservice.domain.entity.LoanEntity;
import sberbank.loanservice.domain.entity.LoanRateEntity;
import sberbank.loanservice.exception.NotFoundLoanRate;
import sberbank.loanservice.repository.LoanRateRepository;
import sberbank.loanservice.repository.LoanRepository;

/**
 * Реализация сервиса для работы с кредитами
 */
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final LoanRateRepository loanRateRepository;
    private final CommonService commonService;


    @Override
    @Transactional
    public LoanDto createLoan(LoanCreateDto dto) {
        LoanRateEntity rateEntity = loanRateRepository.findById(dto.getRateId())
                .orElseThrow(() -> new NotFoundLoanRate("Тариф с id " + dto.getRateId() + " не найден"));

        LoanEntity entity = new LoanEntity(dto.getLoanPeriod(), dto.getLoanAmount(),
                dto.getAccountDebitingId(), dto.getAccountReplenishmentId(),
                dto.getRateId(),
                calculatePayment(rateEntity.getRate(), dto.getLoanAmount(), dto.getLoanPeriod()),
                dto.getClientId(), calculateCommonDebt(rateEntity.getRate(), dto.getLoanAmount())
        );

        loanRepository.save(entity);

        return loanEntityToDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public LoanDto getLoan(Long loanId) {
        return loanEntityToDto(loanRepository.findById(loanId)
                .orElseThrow(() -> new NotFoundLoanRate("Кредит с id " + loanId + " не найден"))
        );
    }

    @Override
    @Transactional()
    public LoanDto repayLoan(LoanRepayDto dto) {
        commonService.withdrawAccount(dto.getAccountId(), dto.getAmount());

        LoanEntity entity = loanRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundLoanRate("Кредит с id " + dto.getId() + " не найден"));

        double balance = entity.getDebt() - dto.getAmount();
        if (balance <= 0) {
            closeLoan(dto.getId());

            if (balance < 0) {

                commonService.refillAccount(dto.getAccountId(), balance);
            }

            entity.setDebt(0D);
        } else {
            entity.setDebt(balance);
        }

        loanRepository.save(entity);

        return loanEntityToDto(entity);

    }

    private void closeLoan(Long loanId) {
        loanRateRepository.deleteById(loanId);
    }

    private LoanDto loanEntityToDto(LoanEntity entity) {
        LoanRateEntity rateEntity = loanRateRepository.findById(entity.getRateId())
                .orElseThrow(() -> new NotFoundLoanRate("Тариф с id " + entity.getRateId() + " не найден"));

        return new LoanDto(
                entity.getId(),
                new LoanRateDto(rateEntity.getId(), rateEntity.getName(), rateEntity.getRate(),
                        commonService.getUser(rateEntity.getEmployeeId())
                ),
                entity.getLoanPeriod(),
                entity.getLoanAmount(),
                entity.getAccountDebitingId(),
                entity.getDebt()
        );
    }

    private Double calculatePayment(Integer rate, Double amount, Integer loanPeriod) {
        return (amount + (amount * rate / 100)) / 12 / loanPeriod;
    }

    private Double calculateCommonDebt(Integer rate, Double amount) {
        return amount + amount * rate / 100;
    }

}
