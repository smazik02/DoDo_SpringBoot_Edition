package com.stanley.dodospring.mappers.impl;

import com.stanley.dodospring.domain.dto.NoteDto;
import com.stanley.dodospring.domain.entities.NoteEntity;
import com.stanley.dodospring.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper implements Mapper<NoteEntity, NoteDto> {

    private final ModelMapper modelMapper;

    public NoteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public NoteDto mapTo(NoteEntity noteEntity) {
        return modelMapper.map(noteEntity, NoteDto.class);
    }

    @Override
    public NoteEntity mapFrom(NoteDto noteDto) {
        return modelMapper.map(noteDto, NoteEntity.class);
    }
}
