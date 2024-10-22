package com.example.TodoList.repository;

import com.example.TodoList.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

@Repository
public class TaskCustomRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Task> findAllTasks() {
        return mongoTemplate.findAll(Task.class);
    }

    public Task findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Task.class);
    }

    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Task.class);
    }

    public Task save(Task task) {
        return mongoTemplate.save(task);
    }
}
