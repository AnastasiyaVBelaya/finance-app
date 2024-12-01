package by.it_academy.jd2.classifierService.service;

import by.it_academy.jd2.classifierService.DTO.CurrencyDTO;
import by.it_academy.jd2.classifierService.repository.api.ICurrencyRepository;
import by.it_academy.jd2.classifierService.repository.entity.CurrencyEntity;
import by.it_academy.jd2.classifierService.service.api.ICurrencyService;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.it_academy.jd2.exception.CurrencyAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CurrencyService(ICurrencyRepository currencyRepository,
                           ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(CurrencyDTO currencyDTO) {
        CurrencyEntity currencyEntity = modelMapper.map(currencyDTO, CurrencyEntity.class);
        currencyEntity.setUuid(UUID.randomUUID());
        if (currencyRepository.existsByTitle(currencyDTO.getTitle())) {
            throw new CurrencyAlreadyExistsException("Валюта с таким названием уже существует");
        }
        currencyRepository.saveAndFlush(currencyEntity);
    }

    @Override
    public PageOf<CurrencyDTO> findAll(PaginationDTO paginationDTO) {
        Pageable pageable = PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
        Page<CurrencyEntity> pageResult = currencyRepository.findAll(pageable);
        List<CurrencyDTO> currencyDTOs = convertEntitiesToDTOs(pageResult.getContent());

        return buildPageOfResponse(pageResult, currencyDTOs);
    }

    private List<CurrencyDTO> convertEntitiesToDTOs(List<CurrencyEntity> entities) {
        return entities.stream()
                .map(currencyEntity -> modelMapper.map(currencyEntity, CurrencyDTO.class))
                .collect(Collectors.toList());
    }

    private PageOf<CurrencyDTO> buildPageOfResponse(Page<CurrencyEntity> pageResult, List<CurrencyDTO> currencyDTOs) {
        return PageOf.<CurrencyDTO>builder()
                .number(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .first(pageResult.isFirst())
                .numberOfElements(pageResult.getNumberOfElements())
                .last(pageResult.isLast())
                .content(currencyDTOs)
                .build();
    }
}
