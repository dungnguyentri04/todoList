package com.example.TodoList.repository;

import com.example.TodoList.model.Task;

import java.util.List;

public interface CustomRepository {
    public List<Task> findAllTasks();
    public Task findById(String id);
    public void deleteById(String id);
    public Task save(Task task);
    public List<Task> getListTasks(int limit, int minScore, int maxScore, boolean status);
}
