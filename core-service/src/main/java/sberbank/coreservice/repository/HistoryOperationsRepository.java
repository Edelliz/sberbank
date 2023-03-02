package sberbank.coreservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sberbank.coreservice.domain.entity.HistoryOperationsEntity;

import java.util.List;

@Repository
public interface HistoryOperationsRepository extends JpaRepository<HistoryOperationsEntity, Long> {

    List<HistoryOperationsEntity> getHistoryOperationsEntitiesByAccountId(Long accountId);
}
