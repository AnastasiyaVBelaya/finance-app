package by.it_academy.jd2.classifierService.service;

import by.it_academy.jd2.classifierService.DTO.OperationCategoryDTO;
import by.it_academy.jd2.classifierService.repository.api.IOperationCategoryRepository;
import by.it_academy.jd2.classifierService.repository.entity.OperationCategoryEntity;
import by.it_academy.jd2.classifierService.service.api.IOperationCategoryService;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.it_academy.jd2.exception.OperationCategoryAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OperationCategoryService implements IOperationCategoryService {


    private final IOperationCategoryRepository operationCategoryRepository;
    private final ModelMapper modelMapper;

    public OperationCategoryService(IOperationCategoryRepository operationCategoryRepository, ModelMapper modelMapper) {
        this.operationCategoryRepository = operationCategoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public void save(OperationCategoryDTO operationCategoryDTO) {
        OperationCategoryEntity operationCategoryEntity = modelMapper.map(operationCategoryDTO,
                OperationCategoryEntity.class);
        operationCategoryEntity.setUuid(UUID.randomUUID());
        if (operationCategoryRepository.existsByTitle(operationCategoryDTO.getTitle())) {
            throw new OperationCategoryAlreadyExistsException("Категория с таким названием уже существует");
        }
        operationCategoryRepository.saveAndFlush(operationCategoryEntity);
    }

    @Override
    public PageOf<OperationCategoryDTO> findAll(PaginationDTO paginationDTO) {
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
        Page<OperationCategoryEntity> pageResult = operationCategoryRepository.findAll(pageable);
        List<OperationCategoryDTO> operationCategoryDTOS = convertEntitiesToDTOs(pageResult.getContent());

        return buildPageOfResponse(pageResult, operationCategoryDTOS);
    }

    private List<OperationCategoryDTO> convertEntitiesToDTOs(List<OperationCategoryEntity> entities) {
        return entities.stream()
                .map(operationCategoryEntity -> modelMapper.map(operationCategoryEntity,
                        OperationCategoryDTO.class))
                .collect(Collectors.toList());
    }

    private PageOf<OperationCategoryDTO> buildPageOfResponse(Page<OperationCategoryEntity> pageResult,
                                                             List<OperationCategoryDTO> operationCategoryDTOS) {
        return PageOf.<OperationCategoryDTO>builder()
                .number(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .first(pageResult.isFirst())
                .numberOfElements(pageResult.getNumberOfElements())
                .last(pageResult.isLast())
                .content(operationCategoryDTOS)
                .build();
    }
}
