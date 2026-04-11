package ru.steeldeezer.DatbaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASS = "SuBJqUXU1271";

    public static Connection getConnection() throws SQLException{
      return DriverManager.getConnection(URL, USER, PASS);
    }
}
