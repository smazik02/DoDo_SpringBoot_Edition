package com.stanley.dodospring.domain.dto.task;

public record UpdateTaskDto(
        String name,

        String description,

        Boolean isDone
) {
}
