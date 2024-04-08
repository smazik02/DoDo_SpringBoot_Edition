package com.stanley.dodospring.mappers;

import com.stanley.dodospring.domain.dto.user.CreateUserDto;
import com.stanley.dodospring.domain.dto.user.ReturnUserDto;
import com.stanley.dodospring.domain.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public ReturnUserDto mapTo(UserEntity userEntity) {
        return new ReturnUserDto(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getRole());
    }

    public UserEntity mapFrom(CreateUserDto createUserDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserDto.username());
        userEntity.setEmail(createUserDto.email());
        userEntity.setPassword(createUserDto.password());
        if (createUserDto.role() != null)
            userEntity.setRole(createUserDto.role());
        return userEntity;
    }

}
