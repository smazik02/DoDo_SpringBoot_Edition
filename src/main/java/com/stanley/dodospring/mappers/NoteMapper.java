package com.stanley.dodospring.mappers;

import com.stanley.dodospring.domain.dto.note.NoteDto;
import com.stanley.dodospring.domain.entities.NoteEntity;
import com.stanley.dodospring.domain.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NoteMapper {

    public NoteDto mapTo(NoteEntity noteEntity) {
        return new NoteDto(
                noteEntity.getTitle(),
                noteEntity.getBody(),
                noteEntity.getCreatedAt(),
                noteEntity.getIcon(),
                noteEntity.getColor(),
                noteEntity.getUser().getId()
        );
    }

    public NoteEntity mapFrom(NoteDto noteDto) {
        NoteEntity noteEntity = new NoteEntity();
        Optional.ofNullable(noteDto.title()).ifPresent(noteEntity::setTitle);
        Optional.ofNullable(noteDto.body()).ifPresent(noteEntity::setBody);
        Optional.ofNullable(noteDto.icon()).ifPresent(noteEntity::setIcon);
        Optional.ofNullable(noteDto.color()).ifPresent(noteEntity::setColor);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(noteDto.userId());
        noteEntity.setUser(userEntity);

        return noteEntity;
    }

}
