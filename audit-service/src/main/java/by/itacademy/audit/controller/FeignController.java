package by.itacademy.audit.controller;

import by.itacademy.audit.dto.AuditDTO;
import by.itacademy.audit.service.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    private final AuditService auditService;

    public FeignController(AuditService auditService) {
        this.auditService = auditService;
    }

    @PostMapping("/audit")
    public void save(@RequestBody AuditDTO auditDTO) {
/*        auditService.save(auditDTO);*/
    }
}
