package com.example.TodoList.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TaskDTO {
    @NotEmpty(message = "Title can't be not empty")
    private String title;

    @NotEmpty(message = "Description can't be not empty")
    private String description;

    @NotNull(message = "Score can't be not null")
    private int score;

    @NotNull(message = "Status can't be not null")
    private boolean status;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
