package com.stanley.dodospring.domain.dto.note;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record NoteDto(
        String title,
        String body,
        @PastOrPresent(message = "Invalid date")
        Date createdAt,
        String icon,
        String color,
        @NotEmpty
        Long userId
) {
}
