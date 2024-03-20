package com.stanley.dodospring.repositories;

import com.stanley.dodospring.domain.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query("select n from NoteEntity n where n.user.id = :userId")
    Iterable<NoteEntity> findByUserId(@Param("userId") Long userId);

    @Query("select n from NoteEntity n where" +
            "(:title is null or n.title like %:title%)" +
            "and (:body is null or n.body like %:body%)" +
            "and n.user.id = :userId")
    Iterable<NoteEntity> filter(
            @Param("title") String title,
            @Param("body") String body,
            @Param("userId") Long userId);
}
