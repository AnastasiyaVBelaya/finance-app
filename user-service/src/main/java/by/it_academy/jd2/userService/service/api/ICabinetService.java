package by.it_academy.jd2.userService.service.api;

import by.it_academy.jd2.userService.dto.UserCreateDTO;
import by.it_academy.jd2.userService.dto.UserDTO;
import by.it_academy.jd2.userService.dto.UserLoginDTO;
import by.it_academy.jd2.userService.dto.UserRegistrationDTO;

public interface ICabinetService {
    String login(UserLoginDTO userLoginDTO);
    UserDTO me();
    UserCreateDTO convertTo (UserRegistrationDTO userRegistrationDTO);
    void save(UserRegistrationDTO userRegistrationDTO);
}
