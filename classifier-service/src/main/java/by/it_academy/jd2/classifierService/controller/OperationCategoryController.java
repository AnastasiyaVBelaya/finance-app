package by.it_academy.jd2.classifierService.controller;


import by.it_academy.jd2.classifierService.DTO.OperationCategoryDTO;
import by.it_academy.jd2.classifierService.service.api.IOperationCategoryService;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/classifier/operation/category")
public class OperationCategoryController {
    private final IOperationCategoryService operationCategoryService;

    public OperationCategoryController(IOperationCategoryService operationCategoryService) {
        this.operationCategoryService = operationCategoryService;
    }

    @GetMapping
    public PageOf<OperationCategoryDTO> get(@Valid PaginationDTO paginationDTO)  {
        return operationCategoryService.findAll(paginationDTO);
    }
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@Valid @RequestBody OperationCategoryDTO operationCategoryDTO) {
        operationCategoryService.save(operationCategoryDTO);
    }
}
