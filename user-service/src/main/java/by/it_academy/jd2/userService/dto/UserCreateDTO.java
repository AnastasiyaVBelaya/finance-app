package by.it_academy.jd2.userService.dto;

import by.it_academy.jd2.userService.model.UserRole;
import by.it_academy.jd2.userService.model.UserStatus;
import by.it_academy.jd2.userService.service.validation.ValidEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dtCreate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dtUpdate;

    @NotBlank
    @Email(message = "Некорректный формат электронной почты")
    private String mail;

    @NotNull
    @Size(min = 1, max = 100, message = "ФИО не должно быть пустым и должно содержать не более 100 символов")
    private String fio;

    @NotNull
    @ValidEnum(enumClass = UserRole.class, message = "Некорректная роль")
    private UserRole role;

    @NotNull
    @ValidEnum(enumClass = UserStatus.class, message = "Некорректный статус")
    private UserStatus status;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&*(),.?\":{}|<>])[^\\s].{8,}$",
            message = "Пароль должен содержать хотя бы один специальный символ, цифры, заглавные и строчные буквы, " +
                    "и не должен начинаться или заканчиваться пробелом")
    private String password;

    @Override
    public String toString() {
        return "UserLoginDTO [mail=" + mail + "]";
    }
}
