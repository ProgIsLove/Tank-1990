package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {

    private static DatabaseConnection instance;

    private final Connection connection;
    private final String dbUrl = "jdbc:postgresql://localhost:5432/tankgame";
    private final String dbUser = "postgres";
    private final String dbPassword = "password";

    private DatabaseConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new DataAccessException("Failed to connect to the database", e);
        }
    }

    public static synchronized DatabaseConnection getInstance() throws SQLException {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }
            return instance;
        } catch (SQLException e) {
            throw new DataAccessException("Failed to get database connection", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DataAccessException("Failed to close database connection", e);
        }
    }
}
