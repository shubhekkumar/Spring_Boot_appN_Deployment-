package com.taskmaster.service;

import com.taskmaster.model.Task;
import com.taskmaster.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id));
    }

    public Task createTask(Task task) {
        task.setId(null);
        return repository.save(task);
    }

    public Task updateTask(Long id, Task task) {

        Task existingTask = getTask(id);

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());

        return repository.save(existingTask);
    }

    public void deleteTask(Long id) {

        getTask(id);

        repository.delete(id);
    }
}
