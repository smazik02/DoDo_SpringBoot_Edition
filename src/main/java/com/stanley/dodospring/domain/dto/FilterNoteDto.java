package com.stanley.dodospring.domain.dto;

import jakarta.validation.constraints.NotNull;

public record FilterNoteDto(
        String title,
        String body,
        String icon,
        String color,
        @NotNull
        Long userId

) {
}
