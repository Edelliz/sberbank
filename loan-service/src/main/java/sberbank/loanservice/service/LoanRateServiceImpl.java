package sberbank.loanservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.loanservice.domain.dto.LoanRateCreateDto;
import sberbank.loanservice.domain.dto.LoanRateDto;
import sberbank.loanservice.domain.entity.LoanRateEntity;
import sberbank.loanservice.exception.NotFoundLoanRate;
import sberbank.loanservice.repository.LoanRateRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы с тарифами кредитов
 */
@Service
@RequiredArgsConstructor
public class LoanRateServiceImpl implements LoanRateService {

    private final LoanRateRepository loanRateRepository;
    private final CommonService commonService;

    @Override
    @Transactional
    public LoanRateDto createLoanRate(LoanRateCreateDto dto) {
        LoanRateEntity entity = new LoanRateEntity(dto.getName(), dto.getRate(), dto.getEmployeeId());

        loanRateRepository.save(entity);

        return  loanRateEntityToDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanRateDto> getLoanRates() {
        return loanRateRepository.findAll().stream()
                .map(this::loanRateEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LoanRateDto getLoanRate(Long loanRateId) {
        return loanRateEntityToDto(loanRateRepository.findById(loanRateId)
                .orElseThrow(() -> new NotFoundLoanRate("Тариф с id " + loanRateId + " не найден")));
    }

    @Override
    @Transactional
    public void deleteLoanRate(Long loanRateId) {
        loanRateRepository.deleteById(loanRateId);
    }

    private LoanRateDto loanRateEntityToDto(LoanRateEntity entity) {
        return new LoanRateDto(
                entity.getId(), entity.getName(),
                entity.getRate(), commonService.getUser(entity.getEmployeeId())
        );
    }
}
