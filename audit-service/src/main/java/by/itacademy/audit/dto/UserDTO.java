package by.itacademy.audit.dto;

import by.it_academy.jd2.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    UUID uuid;
    String mail;
    String fio;
    UserRole role;
}
