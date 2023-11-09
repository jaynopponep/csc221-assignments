package com.example.assignment3.service;
import com.example.assignment3.model.Task;
import com.example.assignment3.model.TaskRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService() throws IOException {
        this.taskRepository = new TaskRepository();
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public Task createOrUpdateTask(Task task) throws IOException {
        return taskRepository.save(task);
    }

    public boolean deleteTask(int id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
