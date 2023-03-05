package sberbank.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sberbank.userservice.domain.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT concat( u.SURNAME, ' ',u.NAME) from users u where u.id = :id")
    String getFullNameUserById(Long id);
}
