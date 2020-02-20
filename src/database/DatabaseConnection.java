package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/PhoneBookAssignment";
    private static final String DB_USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public boolean makeConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, PASSWORD);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean closeConnection() {
        if (getConnection() == null)
            return false;
        try {
            getConnection().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
