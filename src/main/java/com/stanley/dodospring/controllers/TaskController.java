package com.stanley.dodospring.controllers;

import com.stanley.dodospring.domain.dto.task.FilterTaskDto;
import com.stanley.dodospring.domain.dto.task.TaskDto;
import com.stanley.dodospring.domain.dto.task.UpdateTaskDto;
import com.stanley.dodospring.services.TaskService;
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
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long id) {
        return taskService.findOne(id)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "user/{id}")
    public ResponseEntity<List<TaskDto>> getTasksByUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(taskService.findByUser(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<List<TaskDto>> filterTasks(@Valid @RequestBody FilterTaskDto filterTaskDto) {
        return new ResponseEntity<>(taskService.filter(filterTaskDto), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto createTaskDto) {
        return taskService.create(createTaskDto)
                .map(task -> new ResponseEntity<>(task, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable("id") Long id,
            @RequestBody UpdateTaskDto updateTaskDto
    ) {
        return taskService.update(id, updateTaskDto)
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskService.delete(id);
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
