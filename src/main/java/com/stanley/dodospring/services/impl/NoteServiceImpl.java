package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.entities.NoteEntity;
import com.stanley.dodospring.repositories.NoteRepository;
import com.stanley.dodospring.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Optional<NoteEntity> findOne(Long id) {
        return noteRepository.findById(id);
    }
}
