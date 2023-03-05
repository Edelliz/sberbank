package sberbank.coreservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sberbank.coreservice.domain.entity.AccountEntity;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    @Query(nativeQuery = true,
            value = "SELECT amount FROM ACCOUNT accnt WHERE accnt.id = :accountId")
    Long getAmount(Long accountId);

    List<AccountEntity> findAccountEntitiesByOwnerId(long ownerId);

}
