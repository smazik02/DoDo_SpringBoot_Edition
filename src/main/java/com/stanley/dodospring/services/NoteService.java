package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.dto.note.FilterNoteDto;
import com.stanley.dodospring.domain.dto.note.NoteDto;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Optional<NoteDto> findOne(Long id);

    List<NoteDto> findByUser(Long userId);

    Optional<NoteDto> create(NoteDto createNoteDto);

    List<NoteDto> filter(FilterNoteDto filterNoteDto);
}
