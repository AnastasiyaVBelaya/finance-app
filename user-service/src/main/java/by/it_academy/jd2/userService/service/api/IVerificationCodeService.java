package by.it_academy.jd2.userService.service.api;

import by.it_academy.jd2.userService.dto.UserCreateDTO;

public interface IVerificationCodeService {
    void send (UserCreateDTO userCreateDTO);
    void verify(String code, String mail);
}
