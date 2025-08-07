package com.yaz.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaz.todolist.repository.TaskRepo;
import com.yaz.todolist.entities.Task;

@Service
public class TaskService {
    // This service can be used to handle business logic related to tasks
    // For example, methods to create, update, delete, and retrieve tasks can be added here

    // Example method to get a list of tasks (to be implemented)
    @Autowired
    private TaskRepo taskRepo;
    
    public List<Task> getTasks() {
      return taskRepo.findAll();
    }

    public Task addTask(Task task) {
        return taskRepo.save(task);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        return taskRepo.save(task);
    }

    // Additional methods for handling tasks can be added here
    public Task setTaskCompleted(Long id, boolean completed) {
        Task task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(completed);
        return taskRepo.save(task);
    }

    
}