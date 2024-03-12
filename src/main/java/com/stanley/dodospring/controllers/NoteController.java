package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.NoteDto;
import com.stanley.dodospring.domain.entities.NoteEntity;
import com.stanley.dodospring.mappers.Mapper;
import com.stanley.dodospring.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final Mapper<NoteEntity, NoteDto> noteMapper;

    public NoteController(NoteService noteService, Mapper<NoteEntity, NoteDto> noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable("id") Long id) {
        Optional<NoteEntity> foundNote = noteService.findOne(id);
        return foundNote.map(noteEntity -> {
            NoteDto foundNoteDto = noteMapper.mapTo(noteEntity);
            return new ResponseEntity<>(foundNoteDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
