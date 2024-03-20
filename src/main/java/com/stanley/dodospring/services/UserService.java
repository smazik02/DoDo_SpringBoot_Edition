package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.dto.UpdateFilterUserDto;
import com.stanley.dodospring.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findOne(Long id);

    List<UserDto> findAll();

    List<UserDto> filter(UpdateFilterUserDto filterUserDto);

    UserDto create(UserDto createUserDto);

    Optional<UserDto> update(Long id, UpdateFilterUserDto updateUserDto);

    void delete(Long id);

    boolean isExists(Long id);

    Optional<UserDto> findOneByEmail(String email);
}
