package by.it_academy.jd2.classifierService.service;

import by.it_academy.jd2.classifierService.repository.api.ICurrencyRepository;
import by.it_academy.jd2.classifierService.repository.api.IOperationCategoryRepository;
import by.it_academy.jd2.classifierService.repository.entity.CurrencyEntity;
import by.it_academy.jd2.classifierService.repository.entity.OperationCategoryEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassifierService {

    private final ICurrencyRepository currencyRepository;
    private final IOperationCategoryRepository operationCategoryRepository;

    public ClassifierService(ICurrencyRepository currencyRepository, IOperationCategoryRepository operationCategoryRepository) {
        this.currencyRepository = currencyRepository;
        this.operationCategoryRepository = operationCategoryRepository;
    }

    public UUID getCurrencyByUuid(UUID uuid) {
        return currencyRepository.findByUuid(uuid)
                .map(CurrencyEntity::getUuid)
                .orElseThrow(() -> new EntityNotFoundException("Currency not found for UUID: " + uuid));
    }

    public UUID getCategoryByUuid(UUID uuid) {
        return operationCategoryRepository.findByUuid(uuid)
                .map(OperationCategoryEntity::getUuid)
                .orElseThrow(() -> new EntityNotFoundException("Category not found for UUID: " + uuid));
    }
}

