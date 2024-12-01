package by.it_academy.jd2.userService.dto;

import by.it_academy.jd2.model.EssenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    UUID uuid;
    LocalDateTime dtCreate;
    LocalDateTime dtUpdate;
    UserAuditDTO user;
    String text;
    EssenceType type;
    String id;
}
