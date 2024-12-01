package by.itacademy.audit.repository;

import by.it_academy.jd2.model.EssenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit", schema = "audit_schema")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuditEntity {
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false, updatable = false)
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", nullable = false)
    private UserEntity user;*/
    @Column(name = "text", nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    EssenceType type;

    @Column(name = "id", nullable = false)
    private String id;

    @PrePersist
    private void prePersist() {
        this.dtCreate = LocalDateTime.now();
        this.dtUpdate = this.dtCreate;
    }
}
