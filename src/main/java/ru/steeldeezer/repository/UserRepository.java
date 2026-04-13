package ru.steeldeezer.repository;
import org.postgresql.core.ParameterList;
import ru.steeldeezer.DatbaseManager.DatabaseManager;
import ru.steeldeezer.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(Connection conn = DatabaseManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                UUID id =(UUID) rs.getObject("id");
                String name = rs.getString("name");
                users.add(new User(name,id));
            }
        } catch (SQLException e){
            throw new RuntimeException("Ошибка при чтении пользоватлей", e);
        }
        return users;
    }
}
