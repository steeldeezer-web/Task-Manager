package ru.steeldeezer.model;

import java.util.UUID;

public class Task {
    private final UUID id;
    private String title;
    private String description;
    private Status status;
    private final UUID userId;
    public Task(User user, String title, String description){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.status = Status.NEW;
        this.userId = user.getUuid();
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }


    public UUID getId() {
        return id;
    }


    public UUID getUserId() {
        return userId;
    }
}
