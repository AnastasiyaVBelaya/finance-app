package by.it_academy.jd2.accountService.controller;

import by.it_academy.jd2.accountService.dto.AccountDTO;
import by.it_academy.jd2.accountService.service.api.IAccountService;
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
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@Valid @RequestBody AccountDTO accountDTO) {
        accountService.save(accountDTO);
    }

    @GetMapping
    public PageOf<AccountDTO> get(@Valid PaginationDTO paginationDTO) {
        return accountService.findAll(paginationDTO);
    }

    @GetMapping("/{uuid}")
    public AccountDTO get(@PathVariable @NotNull UUID uuid) {
        return accountService.findById(uuid);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@PathVariable @NotNull UUID uuid,
                       @PathVariable("dt_update") @NotNull LocalDateTime dtUpdate,
                       @Valid @RequestBody AccountDTO accountDTO) {
        accountService.update(uuid, dtUpdate, accountDTO);
    }
}
