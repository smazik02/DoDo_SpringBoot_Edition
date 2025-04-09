package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.dto.note.FilterNoteDto;
import com.stanley.dodospring.domain.dto.note.NoteDto;
import com.stanley.dodospring.domain.dto.note.UpdateNoteDto;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Optional<NoteDto> findOne(Long id);

    List<NoteDto> findByUser(Long userId);

    Optional<NoteDto> create(NoteDto noteDto);

    Optional<NoteDto> update(Long id, UpdateNoteDto updateNoteDto);

    void delete(Long id);

    List<NoteDto> filter(FilterNoteDto filterNoteDto);
}
