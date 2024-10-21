package com.example.TodoList.service;

import com.example.TodoList.dto.TaskDTO;
import com.example.TodoList.exception.TaskNotFoundException;
import com.example.TodoList.model.Task;
import com.example.TodoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(TaskDTO taskDTO) {
        Task newTask = new Task();
        newTask.setTitle(taskDTO.getTitle());
        newTask.setDescription(taskDTO.getDescription());
        newTask.setScore(taskDTO.getScore());
        newTask.setStatus(taskDTO.isStatus());
        newTask.setCreateTime(LocalDate.now());
        newTask.setLastModified(LocalDate.now());
        return taskRepository.save(newTask);
    }

    @Override
    public Task updateTask(TaskDTO taskDTO,String id) throws Exception {
        Task task = getTaskById(id);
        task.setLastModified(LocalDate.now());
        task.setStatus(taskDTO.isStatus());
        task.setTitle(taskDTO.getTitle());
        task.setScore(taskDTO.getScore());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(String id) throws Exception {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Override
    public Task getTaskById(String id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id + "don't exist"));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getListTasks(int limit, int minscore, int maxscore, boolean status) {
        int count = 0;
        List<Task> taskList = new ArrayList<>();
        for (Task task : getAllTasks()){
            if (task.isStatus() == status && task.getScore() >= minscore && task.getScore() <= maxscore) taskList.add(task);
            count ++;
            if (count >= limit) break;
        }
        return taskList;
    }
}
