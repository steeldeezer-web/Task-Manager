package ru.steeldeezer.repository;
import ru.steeldeezer.DatbaseManager.DatabaseManager;
import ru.steeldeezer.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
