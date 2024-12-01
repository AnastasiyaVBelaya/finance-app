package by.it_academy.jd2.accountService.repository.entity;

import by.it_academy.jd2.accountService.model.Type;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts", schema = "account")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountEntity {
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false, updatable = false)
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "currency", nullable = false)
    private UUID currency;

    @PrePersist
    private void prePersist() {
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = this.dtCreate;
    }
}
