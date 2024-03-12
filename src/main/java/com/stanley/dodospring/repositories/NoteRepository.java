package com.stanley.dodospring.repositories;

import com.stanley.dodospring.domain.entities.NoteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends CrudRepository<NoteEntity, Long> {
    @Query("select n from NoteEntity n where n.user.id = :userId")
    Iterable<NoteEntity> findByUserId(@Param("userId") Long userId);
}
