package com.stanley.dodospring.domain.dto.task;

import jakarta.validation.constraints.NotNull;

public record FilterTaskDto(
        String name,

        String description,

        Boolean isDone,

        @NotNull(message = "Cannot filter without an owner's id")
        Long userId
) {
}
