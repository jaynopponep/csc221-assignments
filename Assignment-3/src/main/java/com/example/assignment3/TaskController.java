package com.example.assignment3;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping()
    public String index() throws IOException {
        return "Welcome to the API!";
    }

    @PostMapping("/create")
    public String create(@RequestParam("id") int id, @RequestParam("description") String description, @RequestParam("completed") Boolean completed) throws IOException {
        Task newTask = new Task(id, description, completed);
        Task savedTask = TaskService.createOrUpdateTask(newTask);
        return savedTask.toJson();
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") int id, @RequestParam("description") String description, @RequestParam("completed") boolean completed) throws IOException {
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

    @GetMapping("/all")
    //return json of all tasks
    public List<Task> getAllTasks() {
        return TaskService.getAllTasks();
    }

    @GetMapping("/find")
    //return json of task with given id
    public ResponseEntity<String> getTaskById(@RequestParam("id") int id) {
        Task task = TaskService.getTaskById(id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(task.toJson(), HttpStatus.OK);
        }
    }
}