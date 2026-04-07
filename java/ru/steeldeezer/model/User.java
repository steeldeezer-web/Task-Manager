package ru.steeldeezer.model;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private String name;

    public User(String name){
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }
}
