package by.it_academy.jd2.userService.controller;

import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.it_academy.jd2.userService.dto.UserCreateDTO;
import by.it_academy.jd2.userService.dto.UserDTO;
import by.it_academy.jd2.userService.service.api.IUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        userService.save(userCreateDTO);
    }

    @GetMapping
    public PageOf<UserDTO> get(@Valid PaginationDTO paginationDTO) {
        return userService.findAll(paginationDTO);
    }

    @GetMapping("/{uuid}")
    public UserDTO get(@PathVariable @NotNull UUID uuid) {
        return userService.findById(uuid);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@PathVariable @NotNull UUID uuid,
                       @PathVariable("dt_update") @NotNull LocalDateTime dtUpdate,
                       @Valid @RequestBody UserCreateDTO userCreateDTO) {
        userService.update(uuid, dtUpdate, userCreateDTO);
    }
}
