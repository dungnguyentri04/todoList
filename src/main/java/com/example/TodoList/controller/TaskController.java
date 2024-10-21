package com.example.TodoList.controller;

import com.example.TodoList.dto.TaskDTO;
import com.example.TodoList.model.Task;
import com.example.TodoList.service.TaskService;
import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("")
    public ResponseEntity<?> addTask(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getAllErrors().stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            Task task = taskService.addTask(taskDTO);
            return ResponseEntity.ok().body(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id){
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok().body(task);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable String id){
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok().body("Delete Task : " + id);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable String id, @Valid @RequestBody TaskDTO taskDTO,BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getAllErrors().stream()
                        .map(error -> error.getDefaultMessage())
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            }
            Task task = taskService.updateTask(taskDTO,id);
            return ResponseEntity.ok().body(task);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getListTasks(@RequestParam("limit") int limit,
                                      @RequestParam("minscore") int minscore,
                                      @RequestParam("maxscore") int maxscore,
                                      @RequestParam("status") boolean status){
        List<Task> taskList = taskService.getListTasks(limit,minscore,maxscore,status);
        if (taskList.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.ok().body(taskList);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTask(){
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

}
