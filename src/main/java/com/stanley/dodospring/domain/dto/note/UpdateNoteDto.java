package com.stanley.dodospring.domain.dto.note;

public record UpdateNoteDto(
        String title,
        String body,
        String icon,
        String color
) {
}
