package com.stanley.dodospring.domain.dto;

import com.stanley.dodospring.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private Long id;
    private String title;
    private String body;
    @Getter
    private Date createdAt;
    private String icon;
    private String color;
    private UserEntity user;
}
