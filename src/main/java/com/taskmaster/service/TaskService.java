package com.taskmaster.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taskmaster.model.Task;
import com.taskmaster.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.taskmaster.exception.TaskNotFoundException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    private static final Logger logger =
        LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(Long id) {

    logger.info("Fetching task with id: {}", id);

    return repository.findById(id)
            .orElseThrow(() -> {
                logger.warn("Task not found with id: {}", id);
                return new TaskNotFoundException("Task not found with id: " + id);
            });
    } 

    public Task createTask(Task task) {

    logger.info("Creating new task with title: {}", task.getTitle());

    task.setId(null);

    Task savedTask = repository.save(task);

    logger.info("Task created successfully with id: {}", savedTask.getId());

    return savedTask;
    }

    public Task updateTask(Long id, Task task) {

    logger.info("Updating task with id: {}", id);

    Task existingTask = getTask(id);

    existingTask.setTitle(task.getTitle());
    existingTask.setDescription(task.getDescription());
    existingTask.setStatus(task.getStatus());

    Task updatedTask = repository.save(existingTask);

    logger.info("Task updated successfully: {}", id);

    return updatedTask;
    }

    public void deleteTask(Long id) {
        logger.info("Deleting task with id: {}", id);
        getTask(id);

        repository.delete(id);
	logger.info("Task deleted successfully: {}", id);
    }
}
