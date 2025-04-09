package com.stanley.dodospring.domain.dto.note;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NoteDto(
        @NotEmpty(message = "Title should not be empty")
        String title,

        @NotEmpty(message = "Body should not be empty")
        String body,

        String icon,

        String color,

        @NotNull(message = "Note needs to have an author")
        Long userId
) {
}
