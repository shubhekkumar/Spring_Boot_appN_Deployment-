package com.taskmaster.repository;

import com.taskmaster.model.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Task save(Task task) {

        if (task.getId() == null) {
            task.setId(idGenerator.getAndIncrement());
            task.setCreatedAt(LocalDateTime.now());
        }

        task.setUpdatedAt(LocalDateTime.now());

        tasks.put(task.getId(), task);

        return task;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }

    public boolean exists(Long id) {
        return tasks.containsKey(id);
    }
}
