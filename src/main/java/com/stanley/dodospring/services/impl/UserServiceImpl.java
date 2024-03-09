package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.entities.UserEntity;
import com.stanley.dodospring.repositories.UserRepository;
import com.stanley.dodospring.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

}
