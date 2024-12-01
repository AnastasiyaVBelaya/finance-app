package by.it_academy.jd2.userService.repository.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "verification_codes", schema = "users_schema")
public class VerificationCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID verificationCode;

    @Column(name = "code", nullable = false, length = 36)
    private String code;

    @Column(name = "expiry_time", nullable = false)
    private LocalDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
