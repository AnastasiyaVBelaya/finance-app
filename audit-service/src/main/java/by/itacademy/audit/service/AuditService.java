package by.itacademy.audit.service;

import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.itacademy.audit.dto.AuditDTO;
import by.itacademy.audit.repository.AuditEntity;
import by.itacademy.audit.repository.api.IAuditRepository;
import by.itacademy.audit.service.api.IAuditService;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuditService implements IAuditService {

    private final IAuditRepository auditRepository;
    private final ModelMapper modelMapper;

    public AuditService(IAuditRepository accountRepository, ModelMapper modelMapper) {
        this.auditRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PageOf<AuditDTO> findAll(PaginationDTO paginationDTO) {
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
        Page<AuditEntity> auditEntities = auditRepository.findAll(pageable);

        List<AuditDTO> auditDTOS = auditEntities.stream()
                .map(auditEntity -> modelMapper.map(auditEntity, AuditDTO.class))
                .collect(Collectors.toList());

        return buildPageOfResponse(auditEntities, auditDTOS);
    }

    private PageOf<AuditDTO> buildPageOfResponse(Page<AuditEntity> accountEntities, List<AuditDTO> accountDTOS) {
        return PageOf.<AuditDTO>builder()
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
    public AuditDTO findById(UUID uuid) {
        return auditRepository.findById(uuid)
                .map(auditEntity -> modelMapper.map(auditEntity, AuditDTO.class))
                .orElseThrow(() ->
                        new EntityNotFoundException("Действие с UUID " + uuid + " не найдено"));
    }

/*    @Transactional
    public void createAudit(AuditDTO auditDTO) {
        AuditEntity auditEntity = AuditEntity.builder()
                .uuid(UUID.randomUUID())
                .user(auditDTO.getUser())
                .text(auditDTO.getText())
                .type(auditDTO.getType())
                .id(auditDTO.getId())
                .build();

        auditRepository.save(auditEntity);
    }*/
}


