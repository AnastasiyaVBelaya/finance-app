package by.it_academy.jd2.userService.repository.api;

import by.it_academy.jd2.userService.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByMail(String mail);

    @Query("SELECT u.uuid as uuid, u.dtCreate as dtCreate, u.dtUpdate as dtUpdate," +
            " u.mail as mail, u.fio as fio, u.role as role, u.status as status " +
            "FROM UserEntity u")
    Page<IUserProjection> findUserProjections(Pageable pageable);
    Optional<UserEntity> findById(UUID uuid);
    Optional<UserEntity> findByMail(String mail);
}


