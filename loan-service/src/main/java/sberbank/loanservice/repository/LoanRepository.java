package sberbank.loanservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sberbank.loanservice.domain.entity.LoanEntity;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> getLoanEntitiesByClientId(Long clientId);
}
