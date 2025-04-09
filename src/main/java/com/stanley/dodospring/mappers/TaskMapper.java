package com.stanley.dodospring.mappers;

import com.stanley.dodospring.domain.dto.task.TaskDto;
import com.stanley.dodospring.domain.entities.TaskEntity;
import com.stanley.dodospring.domain.entities.UserEntity;

import java.util.Optional;

public class TaskMapper {

    public TaskDto mapTo(TaskEntity taskEntity) {
        return new TaskDto(
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getIsDone(),
                taskEntity.getId()
        );
    }

    public TaskEntity mapFrom(TaskDto taskDto) {
        var taskEntity = new TaskEntity();
        Optional.ofNullable(taskDto.name()).ifPresent(taskEntity::setName);
        Optional.ofNullable(taskDto.description()).ifPresent(taskEntity::setDescription);
        Optional.ofNullable(taskDto.isDone()).ifPresent(taskEntity::setIsDone);

        var userEntity = new UserEntity();
        userEntity.setId(taskDto.userId());
        taskEntity.setUser(userEntity);

        return taskEntity;
    }

}
