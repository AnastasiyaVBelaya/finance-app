package by.it_academy.jd2.accountService.dto;

import by.it_academy.jd2.accountService.model.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dtCreate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dtUpdate;

    private String title;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance;

    private Type type;

    private UUID currency;
}
