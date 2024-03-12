package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.UserDto;
import com.stanley.dodospring.domain.entities.UserEntity;
import com.stanley.dodospring.mappers.Mapper;
import com.stanley.dodospring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return userService.findOne(id)
                .map(userEntity -> new ResponseEntity<>(userMapper.mapTo(userEntity), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserEntity> users = userService.findAll();
        return new ResponseEntity<>(users.stream().map(userMapper::mapTo).toList(), HttpStatus.OK);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<List<UserDto>> filterUsers(@RequestBody UserDto filterUserDto) {
        List<UserEntity> filteredUsers = userService.filter(filterUserDto);
        return new ResponseEntity<>(filteredUsers.stream().map(userMapper::mapTo).toList(), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto createUserDto) {
        UserEntity userEntity = new UserEntity(
                createUserDto.getUsername(),
                createUserDto.getEmail(),
                createUserDto.getPassword()
        );
        UserEntity savedUserEntity = userService.create(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto updateUserDto) {
        Optional<UserEntity> editedUser = userService.update(id, updateUserDto);
        return editedUser
                .map(userEntity -> new ResponseEntity<>(userMapper.mapTo(userEntity), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
