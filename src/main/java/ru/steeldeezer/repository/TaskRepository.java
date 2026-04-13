package ru.steeldeezer.repository;
import ru.steeldeezer.DatbaseManager.DatabaseManager;
import ru.steeldeezer.model.Status;
import ru.steeldeezer.model.Task;
import ru.steeldeezer.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskRepository {
    public void save(Task task){
        String sql ="INSERT INTO tasks (id, title, description, status, user_id) VALUES(?, ?, ?, ?, ?)";
        try(Connection conn = DatabaseManager.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setObject(1,task.getId());
            pstmt.setString(2, task.getTitle());
            pstmt.setString(3, task.getDescription());
            pstmt.setString(4, task.getStatus().name());
            pstmt.setObject(5, task.getUserId());

            pstmt.executeUpdate();
            System.out.println("[DB] Таск сохране: " + task.getTitle());


        } catch (SQLException e){
            throw new RuntimeException("Ошибка сохранения таска в БД", e);
        }
    }
    public List<Task> findByUserId(UUID userId){
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = ?";

        try(Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setObject(1,userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                UUID id = (UUID) rs.getObject("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Status status =  Status.valueOf(rs.getString("status"));
                UUID ownerId  = (UUID) rs.getObject("user_id");
                tasks.add(new Task(id, title, description, status, ownerId));
            }
        }catch (SQLException e){
                throw  new RuntimeException("Ошибка при чтении задач", e);
        }
        return tasks;
    }
}
