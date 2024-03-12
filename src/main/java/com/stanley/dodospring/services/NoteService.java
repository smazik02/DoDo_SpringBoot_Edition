package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.entities.NoteEntity;

import java.util.Optional;

public interface NoteService {
    Optional<NoteEntity> findOne(Long id);
}
