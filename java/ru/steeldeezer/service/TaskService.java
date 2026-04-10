package ru.steeldeezer.service;
import ru.steeldeezer.exception.UserNotFoundException;
import ru.steeldeezer.repository.UserRepository;
import ru.steeldeezer.model.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TaskService {
    private static Map<UUID, User> userMap = new ConcurrentHashMap<>();
    private static Map<UUID, Task> taskMap = new ConcurrentHashMap<>();
    // МЕТОД 1: Регистрация юзера
    public User createUser(String name){
        User user = new User(name);
        userMap.put(user.getUuid(), user);
        UserRepository userRepository = new UserRepository();
        userRepository.save(user);
        return user;
    }
    //потокобезопасное создание таска
    public Task createTask(UUID userId,String title, String description){
        return taskMap.compute(UUID.randomUUID(),(id, existingTask) ->{
            User user = userMap.get(userId);
            if(user == null) throw  new UserNotFoundException("Пользователь не найден");
            return new Task(user, title, description);
        });
    }
}
