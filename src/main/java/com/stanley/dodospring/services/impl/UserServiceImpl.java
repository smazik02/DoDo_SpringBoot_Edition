package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.dto.UserDto;
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
    public List<UserEntity> filter(UserDto filterUserDto) {
        return StreamSupport
                .stream(userRepository.filter(
                        filterUserDto.getUsername(),
                        filterUserDto.getEmail(),
                        filterUserDto.getPassword(),
                        filterUserDto.getRole()).spliterator(), false)
                .toList();
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Optional<UserEntity> update(Long id, UserDto updateUserDto) {
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(updateUserDto.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(updateUserDto.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(updateUserDto.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(updateUserDto.getRole()).ifPresent(existingUser::setRole);
            return userRepository.save(existingUser);
        });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
