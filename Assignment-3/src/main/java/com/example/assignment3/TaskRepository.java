package com.example.assignment3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private final File jsonFile;
    private final ObjectMapper objectMapper;
    private List<Task> tasks;
    public TaskRepository() throws IOException {
        this.jsonFile = new File("task.json");
        this.objectMapper = new ObjectMapper();
        if (jsonFile.exists()) {
            tasks = objectMapper.readValue(jsonFile, new TypeReference<List<Task>>(){});
        } else {
            tasks = new ArrayList<>();
            saveTasks();
        }
    }
    private void saveTasks() throws IOException {
        objectMapper.writeValue(jsonFile, tasks);
    }
    public List<Task> findAll() {
        return tasks;
    }
    public void deleteById(Integer id) throws IOException {
        tasks.removeIf(task -> id.equals(task.getId()));
        saveTasks();
    }
    public Optional<Task> findById(Integer id) {
        return tasks.stream().filter(task -> id.equals(task.getId())).findFirst();
    }
    public Task save(Task task) throws IOException {
        if (findById(task.getId()).isEmpty()) {
            tasks.add(task);
        } else {
            deleteById(task.getId());
            tasks.add(task);
        }
        saveTasks();
        return task;
    }


    public static void main(String[] args) {
        try {
            TaskRepository repository = new TaskRepository();
            Task newTask = new Task(2000, "Test Task 1", false);
            Task savedTask = repository.save(newTask);
            System.out.println("Saved Task: " + savedTask.toJson());

            List<Task> allTasks = repository.findAll();
            System.out.println("All Tasks: ");
            allTasks.forEach(task -> System.out.println(task.toJson()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
