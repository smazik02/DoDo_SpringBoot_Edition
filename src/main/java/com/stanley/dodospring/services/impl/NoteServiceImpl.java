package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.dto.FilterNoteDto;
import com.stanley.dodospring.domain.dto.NoteDto;
import com.stanley.dodospring.domain.entities.NoteEntity;
import com.stanley.dodospring.mappers.NoteMapper;
import com.stanley.dodospring.repositories.NoteRepository;
import com.stanley.dodospring.repositories.UserRepository;
import com.stanley.dodospring.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.noteMapper = noteMapper;
    }

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
    public Optional<NoteDto> create(NoteDto createNoteDto) {
        return userRepository.findById(createNoteDto.userId()).map(noteAuthor -> {
            NoteEntity noteEntity = noteMapper.mapFrom(createNoteDto);
            NoteEntity createdNote = noteRepository.save(noteEntity);
            return noteMapper.mapTo(createdNote);
        });
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
