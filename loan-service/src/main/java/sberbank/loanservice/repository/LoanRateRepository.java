package sberbank.loanservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sberbank.loanservice.domain.entity.LoanRateEntity;

@Repository
public interface LoanRateRepository extends JpaRepository<LoanRateEntity, Long> {
}
