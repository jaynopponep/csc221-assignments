package com.example.assignment3;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return TaskService.getAllTasks();
    }

    @PostMapping("/task")
    public String create(@RequestParam("id") int id, @RequestParam("description") String description, @RequestParam("completed") boolean completed) throws IOException {
        Task newTask = new Task(id, description, completed);
        Task savedTask = TaskService.createOrUpdateTask(newTask);
        return savedTask.toJson();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("id") int id) {
        boolean deleted = TaskService.deleteTask(id);
        if (deleted) {
            return new ResponseEntity<>("Task deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

}
