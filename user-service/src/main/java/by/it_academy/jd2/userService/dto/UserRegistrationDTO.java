package by.it_academy.jd2.userService.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDTO {
    @NotBlank
    @Email(message = "Некорректный формат электронной почты")
    private String mail;
    @NotNull
    @Size(min = 1, max = 100,
            message = "ФИО не должно быть пустым и должно содержать не более 100 символов")
    private String fio;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%^&*(),.?\":{}|<>])[^\\s].{8,}$",
            message = "Пароль должен содержать хотя бы один специальный символ, цифры, заглавные и строчные буквы, " +
                    "и не должен начинаться или заканчиваться пробелом")
    private String password;

    @Override
    public String toString() {
        return "UserLoginDTO [mail=" + mail + "]";
    }
}
