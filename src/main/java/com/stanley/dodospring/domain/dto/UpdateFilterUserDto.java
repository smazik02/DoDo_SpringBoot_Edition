package com.stanley.dodospring.domain.dto;

import com.stanley.dodospring.domain.UserRole;
import jakarta.validation.constraints.Email;

public record UpdateFilterUserDto(
        String username,
        @Email(message = "Not a valid email")
        String email,
        String password,
        UserRole role
) {
}
