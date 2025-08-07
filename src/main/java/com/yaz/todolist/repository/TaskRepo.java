package com.yaz.todolist.repository;
import com.yaz.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
                        
    
    
}
