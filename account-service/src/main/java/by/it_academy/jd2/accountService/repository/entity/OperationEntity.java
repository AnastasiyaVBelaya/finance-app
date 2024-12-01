package by.it_academy.jd2.accountService.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "operations", schema = "operation")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OperationEntity {
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false, updatable = false)
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "category", nullable = false)
    private UUID category;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "currency", nullable = false)
    private UUID currency;

    @ManyToOne
    @JoinColumn(name = "account_uuid", referencedColumnName = "uuid", nullable = false)
    private AccountEntity account;

    @PrePersist
    private void prePersist() {
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = this.dtCreate;
    }
}
