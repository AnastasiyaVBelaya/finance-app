package by.it_academy.jd2.userService.repository.entity;

import by.it_academy.jd2.userService.model.UserRole;
import by.it_academy.jd2.userService.model.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "users_schema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false, updatable = false)
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "fio", nullable = false)
    private String fio;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Column(name = "password", nullable = false)
    private String password;

    @PrePersist
    private void prePersist() {
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = this.dtCreate;
    }
}
