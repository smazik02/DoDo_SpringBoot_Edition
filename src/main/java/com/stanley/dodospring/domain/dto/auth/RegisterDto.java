package com.stanley.dodospring.domain.dto.auth;

import com.stanley.dodospring.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegisterDto(
        @NotEmpty(message = "Username should not be empty")
        String username,

        @NotEmpty(message = "Email should not be empty")
        @Email(message = "Not a valid email")
        String email,

        @NotEmpty(message = "Password should not be empty")
        String password,

        UserRole role
) {
}
