package com.stanley.dodospring.domain.dto.user;

import com.stanley.dodospring.domain.UserRole;

public record ReturnUserDto(
        String username,
        String email,
        UserRole role
) {
}
