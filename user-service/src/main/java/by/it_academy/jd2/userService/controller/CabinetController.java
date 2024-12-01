package by.it_academy.jd2.userService.controller;

import by.it_academy.jd2.userService.dto.UserDTO;
import by.it_academy.jd2.userService.dto.UserLoginDTO;
import by.it_academy.jd2.userService.dto.UserRegistrationDTO;
import by.it_academy.jd2.userService.service.api.ICabinetService;
import by.it_academy.jd2.userService.service.api.IVerificationCodeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/cabinet")
public class CabinetController {

    private final ICabinetService cabinetService;
    private final IVerificationCodeService verificationCodeService;

    public CabinetController(ICabinetService cabinetService,
                             IVerificationCodeService verificationCodeService) {
        this.cabinetService = cabinetService;
        this.verificationCodeService = verificationCodeService;
    }

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        cabinetService.save(userRegistrationDTO);
    }

    @GetMapping("/verification")
    public void get(@RequestParam @NotBlank String code,
                    @RequestParam @NotBlank @Email String mail) {
        verificationCodeService.verify(code, mail);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDTO loginDto) {
        return cabinetService.login(loginDto);
    }

    @GetMapping("/me")
    public UserDTO me() {
        return cabinetService.me();
    }
}

