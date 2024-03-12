package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.NoteDto;
import com.stanley.dodospring.domain.entities.NoteEntity;
import com.stanley.dodospring.mappers.Mapper;
import com.stanley.dodospring.services.NoteService;
import com.stanley.dodospring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;
    private final Mapper<NoteEntity, NoteDto> noteMapper;

    public NoteController(NoteService noteService, UserService userService, Mapper<NoteEntity, NoteDto> noteMapper) {
        this.noteService = noteService;
        this.userService = userService;
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

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<List<NoteDto>> getNoteByUser(@PathVariable("id") Long userId) {
        List<NoteEntity> foundNotes = noteService.findByUser(userId);
        return new ResponseEntity<>(foundNotes.stream().map(noteMapper::mapTo).toList(), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<NoteDto> createNote(@PathVariable("id") Long userId, @RequestBody NoteDto createNoteDto) {
        if (!userService.isExists(userId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        NoteEntity noteEntity = new NoteEntity(
                createNoteDto.getTitle(),
                createNoteDto.getBody(),
                createNoteDto.getIcon(),
                createNoteDto.getColor()
        );
        NoteEntity savedNote = noteService.create(userId, noteEntity);
        return new ResponseEntity<>(noteMapper.mapTo(savedNote), HttpStatus.CREATED);
    }

}
