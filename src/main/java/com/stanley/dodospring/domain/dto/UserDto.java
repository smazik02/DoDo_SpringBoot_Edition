package com.stanley.dodospring.domain.dto;

import com.stanley.dodospring.domain.UserRole;
import com.stanley.dodospring.domain.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private List<NoteEntity> notes;
}
