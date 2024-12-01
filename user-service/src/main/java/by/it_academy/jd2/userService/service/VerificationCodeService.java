package by.it_academy.jd2.userService.service;


import by.it_academy.jd2.exception.VerificationCodeExpiredException;
import by.it_academy.jd2.exception.VerificationCodeNotFoundException;
import by.it_academy.jd2.userService.dto.UserCreateDTO;
import by.it_academy.jd2.userService.model.UserStatus;
import by.it_academy.jd2.userService.repository.api.IVerificationCodeRepository;
import by.it_academy.jd2.userService.repository.entity.UserEntity;
import by.it_academy.jd2.userService.repository.entity.VerificationCodeEntity;
import by.it_academy.jd2.userService.service.api.IEmailService;
import by.it_academy.jd2.userService.service.api.IUserService;
import by.it_academy.jd2.userService.service.api.IVerificationCodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VerificationCodeService implements IVerificationCodeService {
    private final IVerificationCodeRepository verificationCodeRepository;
    private final IEmailService emailService;
    private final IUserService userService;

    public VerificationCodeService(IVerificationCodeRepository verificationCodeRepository,
                                   IEmailService emailService,
                                   IUserService userService) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void send(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = userService.findByMail(userCreateDTO.getMail());
        String verificationCode = UUID.randomUUID().toString();

        VerificationCodeEntity codeEntity = VerificationCodeEntity.builder()
                .code(verificationCode)
                .expiryDate(LocalDateTime.now().plusHours(72))
                .user(userEntity)
                .build();

        verificationCodeRepository.saveAndFlush(codeEntity);

        emailService.sendVerificationEmail(userCreateDTO.getMail(), verificationCode);
    }

    @Override
    @Transactional
    public void verify(String code, String mail) {

        UserEntity userEntity = userService.findByMail(mail);

        VerificationCodeEntity verificationCodeEntity = verificationCodeRepository.findByCodeAndUser(code, userEntity)
                .orElseThrow(() -> new VerificationCodeNotFoundException("Неверный код верификации"));

        if (verificationCodeEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new VerificationCodeExpiredException("Срок действия кода верификации истек");
        }

        userEntity.setStatus(UserStatus.ACTIVATED);

        userService.update(userEntity);
    }
}