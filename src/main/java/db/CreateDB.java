package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    private static final String user = "livraria";
    private static final String password = "senha123";
    private static final String dburl = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "livraria";
    private static Statement st = null;
    private static Connection conn = null;

    public static void createDatabase() {
        try {
            conn = DriverManager.getConnection(dburl, user, password);
            st = conn.createStatement();
            String createDataBase = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            st.execute(createDataBase);

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}