package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.dto.auth.JwtDto;
import com.stanley.dodospring.domain.dto.auth.LoginDto;
import com.stanley.dodospring.domain.dto.auth.RegisterDto;
import com.stanley.dodospring.domain.dto.user.ReturnUserDto;

import java.util.Optional;

public interface AuthService {
    Optional<ReturnUserDto> register(RegisterDto registerDto);

    JwtDto login(LoginDto loginDto);
}
