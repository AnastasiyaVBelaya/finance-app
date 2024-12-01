package by.it_academy.jd2.classifierService.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CurrencyDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dtCreate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dtUpdate;

    @NotBlank(message = "Название не может быть пустым")
    @Size(min = 2, message = "Название должно содержать хотя бы 2 символа")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Название должно содержать только латинские буквы")
    private String title;

    @NotBlank(message = "Описание не может быть пустым")
    @Size(min = 1, message = "Описание должно содержать хотя бы 1 символ")
    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    private String description;
}
