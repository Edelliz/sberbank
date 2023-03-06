package sberbank.coreservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sberbank.coreservice.domain.entity.AccountEntity;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findAccountEntitiesByOwnerId(long ownerId);

}
