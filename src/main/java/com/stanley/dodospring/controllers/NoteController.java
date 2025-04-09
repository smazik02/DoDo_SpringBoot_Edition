package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.note.FilterNoteDto;
import com.stanley.dodospring.domain.dto.note.NoteDto;
import com.stanley.dodospring.domain.dto.note.UpdateNoteDto;
import com.stanley.dodospring.services.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable("id") Long id) {
        return noteService.findOne(id)
                .map(note -> new ResponseEntity<>(note, HttpStatus.OK))
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
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto createNoteDto) {
        return noteService.create(createNoteDto)
                .map(note -> new ResponseEntity<>(note, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<NoteDto> updateNote(
            @PathVariable("id") Long id,
            @RequestBody UpdateNoteDto updateNoteDto
    ) {
        return noteService.update(id, updateNoteDto)
                .map(note -> new ResponseEntity<>(note, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {
        noteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        HashMap<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
