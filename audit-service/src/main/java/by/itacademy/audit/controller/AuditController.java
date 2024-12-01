package by.itacademy.audit.controller;

import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.itacademy.audit.dto.AuditDTO;
import by.itacademy.audit.service.api.IAuditService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final IAuditService auditService;

    public AuditController(IAuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public PageOf<AuditDTO> get(@Valid PaginationDTO paginationDTO) {
        return auditService.findAll(paginationDTO);
    }

    @GetMapping("/{uuid}")
    public AuditDTO get(@PathVariable @NotNull UUID uuid) {
        return auditService.findById(uuid);
    }
}
