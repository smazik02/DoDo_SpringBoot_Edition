package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.entities.NoteEntity;
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

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<NoteEntity> findOne(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public List<NoteEntity> findByUser(Long userId) {
        return StreamSupport
                .stream(noteRepository.findByUserId(userId).spliterator(), false)
                .toList();
    }

    @Override
    public NoteEntity create(Long userId, NoteEntity noteEntity) {
        return userRepository.findById(userId).map(noteAuthor -> {
            noteEntity.setUser(noteAuthor);
            return noteRepository.save(noteEntity);
        }).orElseThrow(() -> new RuntimeException("User doesn't exist"));
    }

}
