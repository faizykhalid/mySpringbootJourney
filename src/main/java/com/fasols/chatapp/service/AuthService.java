package com.fasols.chatapp.service;

import com.fasols.chatapp.dao.PasswordResetTokenRepository;
import com.fasols.chatapp.dao.UserRepository;
import com.fasols.chatapp.entity.PasswordResetToken;
import com.fasols.chatapp.entity.User;
import com.fasols.chatapp.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.Callable;

@Service
@Valid
@AllArgsConstructor
@NoArgsConstructor
public class AuthService {

    UserRepository authUserService;
    PasswordResetTokenRepository passResetTokenRepo;
    EmailService emailService;

    @Autowired
    public void setAuthUserService(UserRepository authUserService) {
        this.authUserService = authUserService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setPassResetTokenRepo(PasswordResetTokenRepository passResetTokenRepo) {
        this.passResetTokenRepo = passResetTokenRepo;
    }

    public void resetPassword(@NotBlank String email){
        Optional<User> user = this.authUserService.findByEmail(email);
        if (user.isPresent()) {
            PasswordResetToken token = new PasswordResetToken(user.get().getId(),Instant.now().plusSeconds(Duration.ofMinutes(10).toSeconds()));
            this.passResetTokenRepo.save(token);
            new Thread(()-> {
                try {
                    emailService.sendEmail(user.get().getEmail(),"Password Reset Request", "Hi,\n We received a password reset request here is the url below:\n\n\nRegards,");
                } catch (Exception e) {
                    this.passResetTokenRepo.delete(token);
                    throw e;
                }
            }).start();
        } else {
            throw new UserNotFoundException("Invalid Email id");
        }
    }
}
