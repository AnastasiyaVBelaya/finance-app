package by.it_academy.jd2.userService.service;

import by.it_academy.jd2.exception.LoginFailedException;
import by.it_academy.jd2.exception.UserNotFoundException;
import by.it_academy.jd2.userService.dto.*;
import by.it_academy.jd2.userService.model.UserRole;
import by.it_academy.jd2.userService.model.UserStatus;
import by.it_academy.jd2.userService.repository.entity.UserEntity;
import by.it_academy.jd2.userService.service.api.ICabinetService;
import by.it_academy.jd2.userService.service.api.IUserService;
import by.it_academy.jd2.userService.service.api.IVerificationCodeService;
import jakarta.transaction.Transactional;
import org.bouncycastle.i18n.LocalizedException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import by.it_academy.jd2.userService.controller.utils.JwtTokenHandler;

@Service
public class CabinetService implements ICabinetService {

    private final IUserService userService;
    private final IVerificationCodeService verificationCodeService;
    private final JwtTokenHandler jwtHandler;
    private final PasswordEncoder passwordEncoder;
    private final UserHolder holder;
    private final ModelMapper modelMapper;

    public CabinetService(IUserService userService,
                          IVerificationCodeService verificationCodeService,
                          JwtTokenHandler jwtHandler,
                          PasswordEncoder passwordEncoder,
                          UserHolder holder,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
        this.jwtHandler = jwtHandler;
        this.passwordEncoder = passwordEncoder;
        this.holder = holder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void save(UserRegistrationDTO userRegistrationDTO) {
        UserCreateDTO userCreateDTO = this.convertTo(userRegistrationDTO);
        userService.save(userCreateDTO);
        verificationCodeService.send(userCreateDTO);
    }

    @Transactional
    @Override
    public String login(UserLoginDTO loginDto) {
        UserEntity userEntity;
        try {
            userEntity = userService.findByMail(loginDto.getMail());
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException("Логин или пароль неверный");
        }

        if (userEntity.getStatus() == UserStatus.WAITING_ACTIVATION) {
            throw new LoginFailedException("Вы не верифицированы");
        }

        if (!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new UserNotFoundException("Логин или пароль неверный");
        }

        UserForTokenDTO userForTokenDTO = modelMapper.map(userEntity, UserForTokenDTO.class);
        return jwtHandler.generateAccessToken(userForTokenDTO);
    }

    @Override
    public UserDTO me() {
        return holder.getUser();
    }

    @Override
    public UserCreateDTO convertTo(UserRegistrationDTO userRegistrationDTO) {
        UserCreateDTO userCreateDTO = modelMapper.map(userRegistrationDTO, UserCreateDTO.class);
        userCreateDTO.setStatus(UserStatus.WAITING_ACTIVATION);
        userCreateDTO.setRole(UserRole.USER);
        return userCreateDTO;
    }
}
