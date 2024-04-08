package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.dto.user.CreateUserDto;
import com.stanley.dodospring.domain.dto.user.ReturnUserDto;
import com.stanley.dodospring.domain.dto.user.UpdateFilterUserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<ReturnUserDto> findOne(Long id);

    List<ReturnUserDto> findAll();

    List<ReturnUserDto> filter(UpdateFilterUserDto filterUserDto);

    ReturnUserDto create(CreateUserDto createUserDto);

    Optional<ReturnUserDto> update(Long id, UpdateFilterUserDto updateUserDto);

    void delete(Long id);

    boolean isExists(Long id);

    Optional<ReturnUserDto> findOneByEmail(String email);
}
