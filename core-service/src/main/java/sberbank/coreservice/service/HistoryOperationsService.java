package sberbank.coreservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.coreservice.domain.dto.AccountOperationDto;
import sberbank.coreservice.domain.entity.HistoryOperationsEntity;
import sberbank.coreservice.domain.entity.TypeOfOperation;
import sberbank.coreservice.repository.HistoryOperationsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryOperationsService {

    private final HistoryOperationsRepository historyOperationsRepository;

    @Transactional
    public void recordOperation(Long accountId, TypeOfOperation type, String parameters) {
        HistoryOperationsEntity entity = new HistoryOperationsEntity();
        entity.setExecuteDate(LocalDateTime.now());
        entity.setText(String.format(type.getDescription(), parameters));
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
            return new AccountOperationDto(e.getText(), e.getExecuteDate());
        }).collect(Collectors.toList());
    }
}
