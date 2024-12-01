package by.itacademy.audit.service.api;

import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.itacademy.audit.dto.AuditDTO;

import java.util.UUID;

public interface IAuditService {
    PageOf<AuditDTO> findAll(PaginationDTO paginationDTO);

    AuditDTO findById(UUID uuid);
/*    void save(AuditDTO auditDTO);*/
}
