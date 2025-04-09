package com.stanley.dodospring.domain.dto.note;

import jakarta.validation.constraints.NotNull;

public record FilterNoteDto(
        String title,

        String body,

        String icon,

        String color,

        @NotNull(message = "Cannot filter without an author's id")
        Long userId
) {
}
