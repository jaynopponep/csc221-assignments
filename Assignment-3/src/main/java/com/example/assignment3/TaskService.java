package com.example.assignment3;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private static TaskRepository taskRepository;

    static {
        try {
            taskRepository = new TaskRepository();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskService() throws IOException {
        taskRepository = new TaskRepository();
    }

    public static List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public static Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public static Task createOrUpdateTask(Task task) throws IOException {
        return taskRepository.save(task);
    }

    public static boolean deleteTask(int id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
