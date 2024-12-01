package by.it_academy.jd2.userService.service.api;



import by.it_academy.jd2.dto.PageOf;
import by.it_academy.jd2.dto.PaginationDTO;
import by.it_academy.jd2.userService.dto.*;
import by.it_academy.jd2.userService.repository.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    void save(UserCreateDTO userCreateDTO);

    PageOf<UserDTO> findAll(PaginationDTO paginationDTO);

    UserDTO findById(UUID uuid);

    UserEntity findByMail(String mail);

    void update(UUID uuid, LocalDateTime dtUpdate, UserCreateDTO userCreateDTO);
    void update (UserEntity userEntity);
}

