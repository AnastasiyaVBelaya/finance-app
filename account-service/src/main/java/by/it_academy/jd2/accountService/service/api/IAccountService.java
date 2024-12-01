package by.it_academy.jd2.accountService.service.api;

import by.it_academy.jd2.accountService.dto.AccountDTO;
import by.it_academy.jd2.accountService.repository.entity.AccountEntity;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IAccountService {
    void save(AccountDTO accountDTO);
    PageOf<AccountDTO> findAll(PaginationDTO paginationDTO);
    AccountDTO findById(UUID uuid);
    void update(UUID uuid, LocalDateTime dtUpdate, AccountDTO accountDTO);
    Optional<AccountEntity> getAccountByUuid(UUID uuid);
}
