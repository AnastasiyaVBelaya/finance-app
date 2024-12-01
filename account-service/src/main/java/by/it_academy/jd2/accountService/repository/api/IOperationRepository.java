package by.it_academy.jd2.accountService.repository.api;

import by.it_academy.jd2.accountService.repository.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IOperationRepository extends JpaRepository<OperationEntity, UUID> {
    Page<OperationEntity> findByAccountUuid(UUID accountUuid, Pageable pageable);
}


