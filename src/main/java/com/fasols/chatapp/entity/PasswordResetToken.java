package com.fasols.chatapp.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class PasswordResetToken {

    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class TokenKey{
        @NotBlank
        private UUID user_id;
        @Size(max=36, min = 36)
        @NotBlank
        private String token;
    }

    public PasswordResetToken(TokenKey key, Instant expiry) {
        this.id = key;
        this.tokenExpiry = expiry;
    }

    public PasswordResetToken(UUID userId, Instant expiry) {
        this.id = new TokenKey(userId, generateSafeToken());
        this.tokenExpiry = expiry;
    }

    @EmbeddedId
    private TokenKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @NotBlank
    private Instant tokenExpiry;

    public static String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[128];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }
}
