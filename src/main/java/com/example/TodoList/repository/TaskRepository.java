package com.example.TodoList.repository;

import com.example.TodoList.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task,String> {
    public List<Task> findByStatusTrue();
    public List<Task> findByStatusFalse();
    public void deleteById(String id);
    public Optional<Task> findById(String id);
}
