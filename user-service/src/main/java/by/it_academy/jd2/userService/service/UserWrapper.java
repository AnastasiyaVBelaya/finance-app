package by.it_academy.jd2.userService.service;

import by.it_academy.jd2.userService.dto.UserDTO;
import by.it_academy.jd2.userService.repository.entity.UserEntity;
import by.it_academy.jd2.userService.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserWrapper {
    private final IUserService userService;
    private final ModelMapper modelMapper;

    public UserWrapper(IUserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    public UserDTO getUser(String mail) {
        UserEntity userEntity=userService.findByMail(mail);
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
