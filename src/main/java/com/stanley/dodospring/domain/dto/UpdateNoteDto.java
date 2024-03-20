package com.stanley.dodospring.domain.dto;

public record UpdateNoteDto(
        String title,
        String body,
        String icon,
        String color
) {
}
