package com.example.TodoList.service;

import com.example.TodoList.dto.TaskDTO;
import com.example.TodoList.model.Task;

import java.util.List;
import java.util.stream.Stream;

public interface TaskService {
    public Task addTask(TaskDTO taskDTO) ;
    public Task updateTask(TaskDTO taskDTO,String id) throws Exception;
    public void deleteTask(String id) throws Exception;
    public Task getTaskById(String id) throws Exception;
    public List<Task> getAllTasks();
    public List<Task> getListTasks(int limit,int minscore,int maxscore,boolean status);

}
