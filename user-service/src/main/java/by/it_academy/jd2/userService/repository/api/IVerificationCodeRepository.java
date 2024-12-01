package by.it_academy.jd2.userService.repository.api;

import by.it_academy.jd2.userService.repository.entity.UserEntity;
import by.it_academy.jd2.userService.repository.entity.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IVerificationCodeRepository extends JpaRepository <VerificationCodeEntity, UUID>{
    Optional<VerificationCodeEntity> findByCodeAndUser(String code, UserEntity user);
}
