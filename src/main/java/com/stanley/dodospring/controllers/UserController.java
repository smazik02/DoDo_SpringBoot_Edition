package com.stanley.dodospring.controllers;

import com.stanley.dodospring.annotations.AdminRole;
import com.stanley.dodospring.domain.dto.user.CreateUserDto;
import com.stanley.dodospring.domain.dto.user.ReturnUserDto;
import com.stanley.dodospring.domain.dto.user.UpdateFilterUserDto;
import com.stanley.dodospring.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @AdminRole
    @GetMapping(path = "/admin/{id}")
    public ResponseEntity<ReturnUserDto> getUserById(@PathVariable("id") Long id) {
        return userService.findOne(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @AdminRole
    @GetMapping(path = "/{email}")
    public ResponseEntity<ReturnUserDto> getUserByEmail(@PathVariable("email") String email) {
        return userService.findOneByEmail(email)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @AdminRole
    @GetMapping(path = "/filter")
    public ResponseEntity<List<ReturnUserDto>> filterUsers(@Valid @RequestBody UpdateFilterUserDto filterUserDto) {
        return new ResponseEntity<>(userService.filter(filterUserDto), HttpStatus.OK);
    }

    @AdminRole
    @GetMapping(path = "/")
    public ResponseEntity<List<ReturnUserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @AdminRole
    @PostMapping(path = "/")
    public ResponseEntity<ReturnUserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.create(createUserDto), HttpStatus.CREATED);
    }

    @AdminRole
    @PatchMapping(path = "/{id}")
    public ResponseEntity<ReturnUserDto> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateFilterUserDto updateUserDto
    ) {
        return userService.update(id, updateUserDto)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @AdminRole
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        HashMap<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
