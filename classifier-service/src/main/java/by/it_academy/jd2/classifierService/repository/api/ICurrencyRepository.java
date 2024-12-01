package by.it_academy.jd2.classifierService.repository.api;

import by.it_academy.jd2.classifierService.repository.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {
    boolean existsByTitle(String title);
    Optional<CurrencyEntity> findByUuid(UUID uuid);
}


