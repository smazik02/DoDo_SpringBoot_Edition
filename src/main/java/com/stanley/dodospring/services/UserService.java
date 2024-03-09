package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findOne(Long id);

    List<UserEntity> findAll();

    UserEntity create(UserEntity userEntity);
}
