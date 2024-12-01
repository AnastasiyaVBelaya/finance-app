package by.it_academy.jd2.accountService.controller;

import by.it_academy.jd2.accountService.dto.OperationDTO;
import by.it_academy.jd2.accountService.service.api.IOperationService;
import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("account")
public class OperationController {

    private final IOperationService operationService;

    public OperationController(IOperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping("/{uuid}/operation")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@PathVariable @NotNull UUID uuid, @Valid @RequestBody OperationDTO operationDTO) {
        operationService.save(uuid, operationDTO);
    }

    @GetMapping("/{uuid}/operation")
    public PageOf<OperationDTO> get(@PathVariable @NotNull UUID uuid,
                                    @Valid PaginationDTO paginationDTO) {
        return operationService.findByAccount(uuid, paginationDTO);
    }

    @PutMapping("/{uuid}/operation/{uuid_operation}/dt_update/{dt_update}")
    public void update(@PathVariable @NotNull UUID uuid,
                       @PathVariable @NotNull UUID uuid_operation,
                       @PathVariable("dt_update") @NotNull LocalDateTime dtUpdate,
                       @Valid @RequestBody OperationDTO operationDTO) {
        operationService.update(uuid, uuid_operation, dtUpdate, operationDTO);
    }

    @DeleteMapping("/{uuid}/operation/{uuid_operation}/dt_update/{dt_update}")
    public void delete(@PathVariable @NotNull UUID uuid,
                       @PathVariable @NotNull UUID uuid_operation,
                       @PathVariable("dt_update") @NotNull LocalDateTime dtUpdate) {
        operationService.delete(uuid, uuid_operation, dtUpdate);
    }
}
