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
        Optional<UserEntity> foundUser = userService.findOne(id);
        return foundUser.map(userEntity -> {
            UserDto foundUserDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(foundUserDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserEntity> users = userService.findAll();
        List<UserDto> userDtos = users.stream().map(userMapper::mapTo).toList();
        return new ResponseEntity<>(users.stream().map(userMapper::mapTo).toList(), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto createUserDto) {
        UserEntity userEntity = new UserEntity(
                createUserDto.getUsername(),
                createUserDto.getEmail(),
                createUserDto.getPassword());
        UserEntity savedUserEntity = userService.create(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto updateUserDto) {
        if (!userService.isExists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        UserEntity editedUser = userService.update(id, updateUserDto);
        return new ResponseEntity<>(userMapper.mapTo(editedUser), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
