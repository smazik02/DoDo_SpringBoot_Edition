package com.stanley.dodospring.domain.dto.task;

import jakarta.validation.constraints.NotNull;

public record TaskDto(
    String name,

    String description,

    Boolean isDone,

    @NotNull(message = "Task needs to have an owner")
    Long userId
) {
}
