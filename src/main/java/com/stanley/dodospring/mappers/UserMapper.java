package com.stanley.dodospring.mappers;

import com.stanley.dodospring.domain.dto.UserDto;
import com.stanley.dodospring.domain.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapTo(UserEntity userEntity) {
        return new UserDto(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole());
    }

    public UserEntity mapFrom(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.username());
        userEntity.setEmail(userDto.email());
        userEntity.setPassword(userDto.password());
        if (userDto.role() != null)
            userEntity.setRole(userDto.role());
        return userEntity;
    }

}
