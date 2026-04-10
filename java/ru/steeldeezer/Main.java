package ru.steeldeezer;

import ru.steeldeezer.model.User;
import ru.steeldeezer.service.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        User user = service.createUser("Антон");

    }
}
