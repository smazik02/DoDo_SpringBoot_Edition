package com.stanley.dodospring.repositories;

import com.stanley.dodospring.domain.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("select t from TaskEntity t where t.user.id = :userId")
    Iterable<TaskEntity> findByUserId(@Param("userId") Long userId);

    @Query("select t from TaskEntity t where" +
            "(:name is null or t.name like %:name%)" +
            "and (:description is null or t.description like %:description%)" +
            "and (:isDone is null or t.isDone = :isDone)" +
            "and t.user.id = :userId")
    Iterable<TaskEntity> filter(@Param("name") String name,
                                @Param("description") String description,
                                @Param("isDone") Boolean isDone,
                                @Param("userId") Long userId);
}
