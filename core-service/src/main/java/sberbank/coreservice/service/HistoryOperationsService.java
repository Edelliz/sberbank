package sberbank.coreservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.coreservice.domain.entity.HistoryOperationsEntity;
import sberbank.coreservice.domain.entity.TypeOfOperation;
import sberbank.coreservice.domain.entity.dto.AccountOperationDto;
import sberbank.coreservice.repository.HistoryOperationsRepository;
import sberbank.coreservice.utils.converter.Converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryOperationsService {

    private final HistoryOperationsRepository historyOperationsRepository;
    private final Converter localDateTimeConverter;

    @Transactional
    public void recordOperation(Long accountId, TypeOfOperation type, String parameters) {
        HistoryOperationsEntity entity = new HistoryOperationsEntity();
        entity.setExecuteDate(LocalDateTime.now());
        entity.setValue(parameters);
        entity.setType(type);
        entity.setAccountId(accountId);

        historyOperationsRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<AccountOperationDto> getAccountHistory(Long accountId) {
        return toDto(historyOperationsRepository.getHistoryOperationsEntitiesByAccountId(accountId));
    }

    private List<AccountOperationDto> toDto(List<HistoryOperationsEntity> entities) {
        return entities.stream().map(e -> {
            return new AccountOperationDto(
                    e.getType().toString(), e.getValue(), localDateTimeConverter.convert(e.getExecuteDate())
            );
        }).collect(Collectors.toList());
    }
}
