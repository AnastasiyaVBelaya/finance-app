package by.it_academy.jd2.userService.dto;

import by.it_academy.jd2.userService.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuditDTO {
    UUID uuid;
    String mail;
    String fio;
    UserRole role;
}
