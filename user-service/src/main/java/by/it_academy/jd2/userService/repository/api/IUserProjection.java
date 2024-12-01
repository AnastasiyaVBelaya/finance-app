package by.it_academy.jd2.userService.repository.api;


import java.time.LocalDateTime;
import java.util.UUID;


public interface IUserProjection {
    UUID getUuid();

    LocalDateTime getDtCreate();

    LocalDateTime getDtUpdate();

    String getMail();

    String getFio();

    String getRole();

    String getStatus();
}
