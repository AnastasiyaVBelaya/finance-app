package by.it_academy.jd2.userService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {
    @NotBlank
    @Email(message = "Некорректный формат электронной почты")
    private String mail;
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
