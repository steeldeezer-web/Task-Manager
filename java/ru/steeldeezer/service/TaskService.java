package ru.steeldeezer.service;
import ru.steeldeezer.model.*;
import java.util.*;
public class TaskService {
    private static Map<UUID, User> userMap = new HashMap<>();
    private static Map<UUID, Task> taskMap = new HashMap<>();
    // МЕТОД 1: Регистрация юзера
    public User createUser(String name){
        User user = new User(name);
        userMap.put(user.getUuid(), user);
        return user;
    }
    public Task createTask(UUID userId,String title, String description){
        if(!userMap.containsKey(userId)){
            throw new RuntimeException("Пользователь не найден");
        }
        User user = userMap.get(userId);
        Task task = new Task(user, title, description);
        taskMap.put(task.getId(), task);
        return task;
    }
}
