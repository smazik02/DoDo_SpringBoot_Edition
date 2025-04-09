package com.stanley.dodospring.services.impl;

import com.stanley.dodospring.domain.dto.task.FilterTaskDto;
import com.stanley.dodospring.domain.dto.task.TaskDto;
import com.stanley.dodospring.domain.dto.task.UpdateTaskDto;
import com.stanley.dodospring.domain.entities.TaskEntity;
import com.stanley.dodospring.mappers.TaskMapper;
import com.stanley.dodospring.repositories.TaskRepository;
import com.stanley.dodospring.repositories.UserRepository;
import com.stanley.dodospring.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public Optional<TaskDto> findOne(Long id) {
        return taskRepository.findById(id).map(taskMapper::mapTo);
    }

    @Override
    public List<TaskDto> findByUser(Long userId) {
        return StreamSupport
                .stream(taskRepository.findByUserId(userId).spliterator(), false)
                .map(taskMapper::mapTo)
                .toList();
    }

    @Override
    public List<TaskDto> filter(FilterTaskDto filterTaskDto) {
        return StreamSupport
                .stream(taskRepository.filter(
                        filterTaskDto.name(),
                        filterTaskDto.description(),
                        filterTaskDto.isDone(),
                        filterTaskDto.userId()).spliterator(), false)
                .map(taskMapper::mapTo)
                .toList();
    }

    @Override
    public Optional<TaskDto> create(TaskDto taskDto) {
        return userRepository.findById(taskDto.userId()).map(taskOwner -> {
            TaskEntity taskEntity = taskMapper.mapFrom(taskDto);
            TaskEntity createdNote = taskRepository.save(taskEntity);
            return taskMapper.mapTo(createdNote);
        });
    }

    @Override
    public Optional<TaskDto> update(Long id, UpdateTaskDto updateTaskDto) {
        return taskRepository.findById(id).map(existingTask -> {
            Optional.ofNullable(updateTaskDto.name()).ifPresent(existingTask::setName);
            Optional.ofNullable(updateTaskDto.description()).ifPresent(existingTask::setDescription);
            Optional.ofNullable(updateTaskDto.isDone()).ifPresent(existingTask::setIsDone);
            TaskEntity editedTask = taskRepository.save(existingTask);
            return taskMapper.mapTo(editedTask);
        });
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

}
