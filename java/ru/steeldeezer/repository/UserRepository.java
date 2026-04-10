package ru.steeldeezer.repository;
import ru.steeldeezer.DatbaseManager.DatabaseManager;
import ru.steeldeezer.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    public void save(User user){
        String sql ="INSERT INTO users (id, name) VALUES(?,?)";
        // try-with-resources сам закроет соединение и стейтмент
        try(Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setObject(1,user.getUuid());
                pstmt.setString(2, user.getName());


                pstmt.executeUpdate();
            System.out.println("[DB] Юзер сохране: " + user.getName());

        } catch (SQLException e){
            throw new RuntimeException("Ошибка сохранения юзера в БД", e);
        }
    }
}
