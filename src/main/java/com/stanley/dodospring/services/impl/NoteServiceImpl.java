package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.dto.note.FilterNoteDto;
import com.stanley.dodospring.domain.dto.note.NoteDto;
import com.stanley.dodospring.domain.dto.note.UpdateNoteDto;
import com.stanley.dodospring.domain.entities.NoteEntity;
import com.stanley.dodospring.mappers.NoteMapper;
import com.stanley.dodospring.repositories.NoteRepository;
import com.stanley.dodospring.repositories.UserRepository;
import com.stanley.dodospring.services.NoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;

    @Override
    public Optional<NoteDto> findOne(Long id) {
        return noteRepository.findById(id).map(noteMapper::mapTo);
    }

    @Override
    public List<NoteDto> findByUser(Long userId) {
        return StreamSupport
                .stream(noteRepository.findByUserId(userId).spliterator(), false)
                .map(noteMapper::mapTo)
                .toList();
    }

    @Override
    @Transactional
    public Optional<NoteDto> create(NoteDto noteDto) {
        return userRepository.findById(noteDto.userId()).map(noteAuthor -> {
            NoteEntity noteEntity = noteMapper.mapFrom(noteDto);
            NoteEntity createdNote = noteRepository.save(noteEntity);
            return noteMapper.mapTo(createdNote);
        });
    }

    @Override
    @Transactional
    public Optional<NoteDto> update(Long id, UpdateNoteDto updateNoteDto) {
        return noteRepository.findById(id).map(existingNote -> {
            Optional.ofNullable(updateNoteDto.title()).ifPresent(existingNote::setTitle);
            Optional.ofNullable(updateNoteDto.body()).ifPresent(existingNote::setBody);
            Optional.ofNullable(updateNoteDto.icon()).ifPresent(existingNote::setIcon);
            Optional.ofNullable(updateNoteDto.color()).ifPresent(existingNote::setColor);
            NoteEntity editedNote = noteRepository.save(existingNote);
            return noteMapper.mapTo(editedNote);
        });
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<NoteDto> filter(FilterNoteDto filterNoteDto) {
        return StreamSupport
                .stream(noteRepository.filter(
                        filterNoteDto.title(),
                        filterNoteDto.body(),
                        filterNoteDto.userId()).spliterator(), false)
                .map(noteMapper::mapTo)
                .toList();
    }

}
