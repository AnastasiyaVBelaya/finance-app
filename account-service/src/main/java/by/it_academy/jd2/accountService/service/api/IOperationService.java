package by.it_academy.jd2.accountService.service.api;

import by.it_academy.jd2.accountService.dto.OperationDTO;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IOperationService {
    void save(UUID accountUuid, OperationDTO operationDTO);

    PageOf<OperationDTO> findByAccount(UUID accountUuid, PaginationDTO paginationDTO);

    void update(UUID accountUuid, UUID operationUuid, LocalDateTime dtUpdate, OperationDTO operationDTO);

    void delete(UUID accountUuid, UUID operationUuid, LocalDateTime dtUpdate);
}

