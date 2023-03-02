package sberbank.coreservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sberbank.coreservice.domain.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query(nativeQuery = true,
            value = "SELECT SUM(amount + :amount) as amount FROM ACCOUNT accnt WHERE accnt.id = :accountId")
    Long replenishAccount(Long accountId, Long amount);

    @Query(nativeQuery = true,
            value = "SELECT SUM(amount - :amount) as amount FROM ACCOUNT accnt WHERE accnt.id = :accountId")
    Long withdrawAccount(Long accountId, Long amount);

    @Query(nativeQuery = true,
            value = "SELECT amount FROM ACCOUNT accnt WHERE accnt.id = :accountId")
    Long getAmount(Long accountId);

    @Query(nativeQuery = true,
            value = "SELECT number FROM ACCOUNT accnt WHERE accnt.id = :accountId")
    String getNumber(Long accountId);
}
