package by.it_academy.jd2.accountService.service;


import by.it_academy.jd2.accountService.dto.OperationDTO;
import by.it_academy.jd2.accountService.repository.api.IOperationRepository;
import by.it_academy.jd2.accountService.repository.entity.AccountEntity;
import by.it_academy.jd2.accountService.repository.entity.OperationEntity;
import by.it_academy.jd2.accountService.service.api.ClassifierFeignClient;
import by.it_academy.jd2.accountService.service.api.IOperationService;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OperationService implements IOperationService {
    private final IOperationRepository operationRepository;
    private final AccountService accountService;
    private final ClassifierFeignClient classifierFeignClient;
    private final ModelMapper modelMapper;

    public OperationService(IOperationRepository operationRepository,
                            AccountService accountService,
                            ClassifierFeignClient classifierFeignClient,
                            ModelMapper modelMapper) {
        this.operationRepository = operationRepository;
        this.accountService = accountService;
        this.classifierFeignClient = classifierFeignClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(UUID accountUuid, OperationDTO operationDTO) {

        AccountEntity accountEntity = accountService.getAccountByUuid(accountUuid)
                .orElseThrow(() -> new IllegalArgumentException("Счет с UUID " + accountUuid + " не найден"));

        OperationEntity operationEntity = modelMapper.map(operationDTO, OperationEntity.class);
        operationEntity.setUuid(UUID.randomUUID());
        operationEntity.setAccount(accountEntity);

        try {
            UUID currencyUuid = operationDTO.getCurrency();
            UUID categoryUuid = operationDTO.getCategory();

            currencyUuid = classifierFeignClient.getCurrencyByUuid(currencyUuid);
            categoryUuid = classifierFeignClient.getCategoryByUuid(categoryUuid);

            operationEntity.setCurrency(currencyUuid);
            operationEntity.setCategory(categoryUuid);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверный формат UUID для валюты или категории", e);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить валюту или категорию", e);
        }
        accountService.save(accountEntity, operationDTO);

        operationRepository.saveAndFlush(operationEntity);
    }

    @Override
    public PageOf<OperationDTO> findByAccount(UUID accountUuid, PaginationDTO paginationDTO) {
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
        Page<OperationEntity> operationEntities = operationRepository.findByAccountUuid(accountUuid, pageable);

        List<OperationDTO> operationDTOS = operationEntities.stream()
                .map(operationEntity -> modelMapper.map(operationEntity, OperationDTO.class))
                .collect(Collectors.toList());

        return buildPageOfResponse(operationEntities, operationDTOS);
    }

    private PageOf<OperationDTO> buildPageOfResponse(Page<OperationEntity> operationEntities, List<OperationDTO> operationDTOS) {
        return PageOf.<OperationDTO>builder()
                .number(operationEntities.getNumber())
                .size(operationEntities.getSize())
                .totalElements(operationEntities.getTotalElements())
                .totalPages(operationEntities.getTotalPages())
                .first(operationEntities.isFirst())
                .numberOfElements(operationEntities.getNumberOfElements())
                .last(operationEntities.isLast())
                .content(operationDTOS)
                .build();
    }

    @Override
    public void update(UUID accountUuid, UUID operationUuid, LocalDateTime dtUpdate, OperationDTO operationDTO) {
        operationRepository.findById(operationUuid).ifPresent(operationEntity -> {
            if (!operationEntity.getDtUpdate().isEqual(dtUpdate)) {
                throw new OptimisticLockingFailureException("Данные были изменены другим пользователем.");
            }
            modelMapper.map(operationDTO, operationEntity);
            operationRepository.saveAndFlush(operationEntity);
        });
    }

    @Override
    public void delete(UUID accountUuid, UUID operationUuid, LocalDateTime dtUpdate) {
        operationRepository.findById(operationUuid).ifPresent(operationEntity -> {
            if (!operationEntity.getDtUpdate().isEqual(dtUpdate)) {
                throw new OptimisticLockingFailureException("Данные были изменены другим пользователем.");
            }
            operationRepository.delete(operationEntity);
        });
    }
}
