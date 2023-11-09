package com.example.assignment3.model;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private final File jsonFile;
    private final ObjectMapper objectMapper;
    private List<Task> tasks;
    public TaskRepository() throws IOException, FileNotFoundException {
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
        if (task.getId() == 0 || findById(task.getId()).isEmpty()) {
            task.setId(tasks.size() + 1);
            tasks.add(task);
        } else {
            int index = task.getId() - 1;
            if (index < tasks.size()) {
                tasks.set(index, task);
            } else {
                throw new IndexOutOfBoundsException("Task ID does not correspond to a valid list index.");
            }
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
