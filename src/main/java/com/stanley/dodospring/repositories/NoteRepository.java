package com.stanley.dodospring.repositories;

import com.stanley.dodospring.domain.entities.NoteEntity;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<NoteEntity, Long> {
}
