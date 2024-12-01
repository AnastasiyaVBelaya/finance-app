package by.it_academy.jd2.accountService.service;

import by.it_academy.jd2.accountService.dto.AccountDTO;
import by.it_academy.jd2.accountService.dto.OperationDTO;
import by.it_academy.jd2.accountService.repository.api.IAccountRepository;
import by.it_academy.jd2.accountService.repository.entity.AccountEntity;
import by.it_academy.jd2.accountService.service.api.ClassifierFeignClient;
import by.it_academy.jd2.accountService.service.api.IAccountService;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;
    private final ClassifierFeignClient classifierFeignClient;
    private final ModelMapper modelMapper;

    public AccountService(IAccountRepository accountRepository, ClassifierFeignClient classifierFeignClient, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.classifierFeignClient = classifierFeignClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(AccountDTO accountDTO) {
        if (accountRepository.existsByTitle(accountDTO.getTitle())) {
            throw new IllegalArgumentException("Счет с таким названием уже существует: " + accountDTO.getTitle());
        }
        UUID currencyUuid = classifierFeignClient.getCurrencyByUuid(accountDTO.getCurrency());
        AccountEntity accountEntity = modelMapper.map(accountDTO, AccountEntity.class);
        accountEntity.setCurrency(currencyUuid);
        accountEntity.setUuid(UUID.randomUUID());
        accountEntity.setBalance(BigDecimal.ZERO);
        accountRepository.saveAndFlush(accountEntity);
    }

    public void save(AccountEntity accountEntity, OperationDTO operationDTO) {
        BigDecimal updatedBalance = accountEntity.getBalance().add(operationDTO.getValue());
        accountEntity.setBalance(updatedBalance);
        accountRepository.saveAndFlush(accountEntity);
    }



    @Override
    public PageOf<AccountDTO> findAll(PaginationDTO paginationDTO) {
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
        Page<AccountEntity> accountEntities = accountRepository.findAll(pageable);

        List<AccountDTO> accountDTOS = accountEntities.stream()
                .map(accountEntity -> modelMapper.map(accountEntity, AccountDTO.class))
                .collect(Collectors.toList());

        return buildPageOfResponse(accountEntities, accountDTOS);
    }


    private PageOf<AccountDTO> buildPageOfResponse(Page<AccountEntity> accountEntities, List<AccountDTO> accountDTOS) {
        return PageOf.<AccountDTO>builder()
                .number(accountEntities.getNumber())
                .size(accountEntities.getSize())
                .totalElements(accountEntities.getTotalElements())
                .totalPages(accountEntities.getTotalPages())
                .first(accountEntities.isFirst())
                .numberOfElements(accountEntities.getNumberOfElements())
                .last(accountEntities.isLast())
                .content(accountDTOS)
                .build();
    }

    @Override
    public AccountDTO findById(UUID uuid) {
        return accountRepository.findById(uuid)
                .map(accountEntity -> modelMapper.map(accountEntity, AccountDTO.class))
                .orElseThrow(() ->
                        new IllegalArgumentException("Счет с UUID " + uuid + " не найден"));
    }

    @Override
    public Optional<AccountEntity> getAccountByUuid(UUID uuid) {
        return Optional.ofNullable(accountRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Счет с UUID " + uuid + " не найден")));
    }


    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, AccountDTO accountDTO) {
        AccountEntity accountEntity = accountRepository.findById(uuid)
                .orElseThrow(() -> new OptimisticLockingFailureException("Счет с UUID " + uuid + " не найден"));

        if (!accountEntity.getDtUpdate().isEqual(dtUpdate)) {
            throw new OptimisticLockingFailureException("Данные были изменены другим пользователем.");
        }
        modelMapper.map(accountDTO, accountEntity);
        accountRepository.saveAndFlush(accountEntity);
    }
}
