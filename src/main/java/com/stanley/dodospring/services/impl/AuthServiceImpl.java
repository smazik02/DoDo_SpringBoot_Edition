package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.dto.auth.JwtDto;
import com.stanley.dodospring.domain.dto.auth.LoginDto;
import com.stanley.dodospring.domain.dto.auth.RegisterDto;
import com.stanley.dodospring.domain.dto.user.ReturnUserDto;
import com.stanley.dodospring.mappers.UserMapper;
import com.stanley.dodospring.repositories.UserRepository;
import com.stanley.dodospring.security.SecurityUser;
import com.stanley.dodospring.services.AuthService;
import com.stanley.dodospring.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public Optional<ReturnUserDto> register(RegisterDto registerDto) {
        try {
            var userEntity = this.userMapper.mapFromWithPassword(registerDto);
            var savedUser = this.userRepository.save(userEntity);
            return Optional.ofNullable(this.userMapper.mapTo(savedUser));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public JwtDto login(LoginDto loginDto) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = (SecurityUser) auth.getPrincipal();
        claims.put("email", user.getUsername());
        return new JwtDto(jwtService.generateToken(claims, user));
    }

}
