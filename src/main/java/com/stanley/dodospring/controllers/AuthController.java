package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.auth.RegisterDto;
import com.stanley.dodospring.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
