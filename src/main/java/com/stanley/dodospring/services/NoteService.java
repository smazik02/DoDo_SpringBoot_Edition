package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.entities.NoteEntity;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Optional<NoteEntity> findOne(Long id);

    NoteEntity create(Long userId, NoteEntity noteEntity);

    List<NoteEntity> findByUser(Long userId);
}
