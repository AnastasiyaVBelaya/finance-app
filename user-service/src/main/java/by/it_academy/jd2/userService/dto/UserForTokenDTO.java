package by.it_academy.jd2.userService.dto;

import by.it_academy.jd2.userService.model.UserRole;
import by.it_academy.jd2.userService.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForTokenDTO {
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
}
