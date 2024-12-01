package by.itacademy.audit.repository.api;

import by.itacademy.audit.repository.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAuditRepository extends JpaRepository<AuditEntity, UUID> {
    Page<AuditEntity> findAll(Pageable pageable);

    Optional<AuditEntity> findById(UUID uuid);
}


