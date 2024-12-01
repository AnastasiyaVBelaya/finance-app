package by.it_academy.jd2.classifierService.repository.api;


import by.it_academy.jd2.classifierService.repository.entity.OperationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IOperationCategoryRepository extends JpaRepository<OperationCategoryEntity, UUID> {
    boolean existsByTitle(String title);
    Optional<OperationCategoryEntity> findByUuid(UUID uuid);
}
