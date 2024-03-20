package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.FilterNoteDto;
import com.stanley.dodospring.domain.dto.NoteDto;
import com.stanley.dodospring.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable("id") Long id) {
        return noteService.findOne(id)
                .map(noteEntity -> new ResponseEntity<>(noteEntity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<List<NoteDto>> getNoteByUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(noteService.findByUser(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<List<NoteDto>> filterNotes(@Valid @RequestBody FilterNoteDto filterNoteDto) {
        return new ResponseEntity<>(noteService.filter(filterNoteDto), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto createNoteDto) {
        return noteService.create(createNoteDto)
                .map(editedNote -> new ResponseEntity<>(editedNote, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PatchMapping(path = "/{id}")
//    public ResponseEntity<NoteDto> updateNote(
//            @PathVariable("id") Long id,
//            @Valid @RequestBody UpdateNoteDto updateNoteDto
//    ) {
//        return noteService.update();
//    }

}
