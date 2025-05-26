package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    private static final String DB_NAME = "BOOKSTORE";
    private static final String USER = "root";
    private static final String PASSWORD = "senha123";
    private static final String DB_URL_WITHOUT_DB = "jdbc:mysql://localhost:3306/";
    private static final String DB_URL_WITH_DB = DB_URL_WITHOUT_DB + DB_NAME;

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL_WITH_DB, USER, PASSWORD)) {
            System.out.println("Banco de dados já existe.");
        } catch (SQLException e) {
            if (e.getMessage().contains("Unknown database")) {
                System.out.println("Banco não encontrado. Criando...");
                create();
            } else {
                throw new DBException("Erro: " + e.getMessage());
            }
        }
    }

    private static void create() {
        try (Connection conn = DriverManager.getConnection(DB_URL_WITHOUT_DB, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Banco criado com sucesso.");

        } catch (SQLException e) {
            throw new DBException("Erro ao criar: " + e.getMessage());
        }
    }
}