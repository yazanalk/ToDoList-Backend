package com.yaz.todolist.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaz.todolist.entities.Task;
import com.yaz.todolist.services.TaskService;
import java.util.List;



import java.util.Map;


@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks(); // This should return a list of tasks from the service
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task savedTask = taskService.addTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(task);
    }

    // Additional methods for handling tasks can be added here
    @PutMapping("/{id}/complete")
    public ResponseEntity<Task> setTaskCompleted(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        boolean completed = body.getOrDefault("completed", true);
        Task task = taskService.setTaskCompleted(id, completed);
        return ResponseEntity.ok(task);
    }
}
