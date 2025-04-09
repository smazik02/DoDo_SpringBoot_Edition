package com.stanley.dodospring.services;

import com.stanley.dodospring.domain.dto.task.FilterTaskDto;
import com.stanley.dodospring.domain.dto.task.TaskDto;
import com.stanley.dodospring.domain.dto.task.UpdateTaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<TaskDto> findOne(Long id);

    List<TaskDto> findByUser(Long userId);

    List<TaskDto> filter(FilterTaskDto filterTaskDto);

    Optional<TaskDto> create(TaskDto taskDto);

    Optional<TaskDto> update(Long id, UpdateTaskDto updateTaskDto);

    void delete(Long id);
}
