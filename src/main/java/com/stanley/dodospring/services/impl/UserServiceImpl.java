package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.dto.UpdateFilterUserDto;
import com.stanley.dodospring.domain.dto.UserDto;
import com.stanley.dodospring.domain.entities.UserEntity;
import com.stanley.dodospring.mappers.UserMapper;
import com.stanley.dodospring.repositories.UserRepository;
import com.stanley.dodospring.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDto> findOne(Long id) {
        return userRepository.findById(id).map(userMapper::mapTo);
    }

    @Override
    public Optional<UserDto> findOneByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::mapTo);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::mapTo)
                .toList();
    }

    @Override
    public List<UserDto> filter(UpdateFilterUserDto filterUserDto) {
        return StreamSupport
                .stream(userRepository.filter(
                        filterUserDto.username(),
                        filterUserDto.email(),
                        filterUserDto.password(),
                        filterUserDto.role()).spliterator(), false)
                .map(userMapper::mapTo)
                .toList();
    }

    @Override
    public UserDto create(UserDto createUserDto) {
        UserEntity userEntity = userMapper.mapFrom(createUserDto);
        UserEntity savedUser = userRepository.save(userEntity);
        return userMapper.mapTo(savedUser);
    }

    @Override
    public Optional<UserDto> update(Long id, UpdateFilterUserDto updateUserDto) {
        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(updateUserDto.username()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(updateUserDto.email()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(updateUserDto.password()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(updateUserDto.role()).ifPresent(existingUser::setRole);
            UserEntity editedUser = userRepository.save(existingUser);
            return userMapper.mapTo(editedUser);
        });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

}
