package za.co.pixelly.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Provides a configuration for connecting to a database using JDBC.
 * It utilizes the `io.github.cdimascio.dotenv` library to load environment
 * variables
 * containing database credentials securely.
 */
public class DatabaseConfig {

    private static final Dotenv dotenv = Dotenv.load();

    /**
     * Establishes a connection to the database using the credentials stored in
     * environment variables.
     *
     * @return A connection object representing the connection to the database.
     * @throws SQLException If an error occurs while connecting to the database.
     */
    public static Connection getConnection() throws SQLException {
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        return DriverManager.getConnection(url, user, password);
    }
}
