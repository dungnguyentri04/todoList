package com.example.TodoList.repository;

import com.example.TodoList.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

@Repository
public class TaskCustomRepository implements CustomRepository{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Task> findAllTasks() {
        return mongoTemplate.findAll(Task.class);
    }

    @Override
    public Task findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Task.class);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Task.class);
    }

    @Override
    public Task save(Task task) {
        return mongoTemplate.save(task);
    }

    @Override
    public List<Task> getListTasks(int limit, int minScore, int maxScore, boolean status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status)
                .and("score").gte(minScore)
                .lte(maxScore));
        query.limit(limit);
        return mongoTemplate.find(query, Task.class);
    }
}
