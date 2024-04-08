package com.stanley.dodospring.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginDto(
        @NotEmpty(message = "Email should not be empty")
        @Email(message = "Not a valid email")
        String email,

        @NotEmpty(message = "Password should not be empty")
        String password
) {
}
