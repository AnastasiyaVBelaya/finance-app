package by.it_academy.jd2.accountService.repository.api;

import by.it_academy.jd2.accountService.repository.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, UUID> {
    boolean existsByTitle(String title);

    Optional<AccountEntity> findById(UUID uuid);

    Page<AccountEntity> findAll(Pageable pageable);
}


