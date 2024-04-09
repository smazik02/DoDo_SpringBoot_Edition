package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.auth.JwtDto;
import com.stanley.dodospring.domain.dto.auth.LoginDto;
import com.stanley.dodospring.domain.dto.auth.RegisterDto;
import com.stanley.dodospring.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        return authService.register(registerDto)
                .map(registeredUser -> new ResponseEntity<>(registeredUser, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }

}
