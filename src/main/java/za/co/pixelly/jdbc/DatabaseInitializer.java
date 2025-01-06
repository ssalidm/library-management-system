package za.co.pixelly.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.pixelly.jdbc.config.DatabaseConfig;

public class DatabaseInitializer {
    private static final Logger logger = Logger.getLogger(DatabaseInitializer.class.getName());

    public static void init() {
        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();) {

            String usersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "first_name VARCHAR(100) NOT NULL, " +
                    "last_name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL);";

            String authorsTable = "CREATE TABLE IF NOT EXISTS authors (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL);";

            String publishersTable = "CREATE TABLE IF NOT EXISTS publishers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    " name VARCHAR(255) NOT NULL);";

            String booksTable = "CREATE TABLE IF NOT EXISTS books (" +
                    "isbn VARCHAR(13) PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "author_id INT NOT NULL, " +
                    "publisher_id INT NOT NULL, " +
                    "year_published INT NOT NULL, " +
                    "description TEXT, " +
                    "FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (publisher_id) REFERENCES publishers(id) ON DELETE CASCADE);";

            String book_copies = "CREATE TABLE IF NOT EXISTS book_copies (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "isbn VARCHAR(13) NOT NULL, " +
                    "available BOOLEAN DEFAULT TRUE, " +
                    "FOREIGN KEY (isbn) REFERENCES books(isbn) ON DELETE CASCADE);";

            String borrowRecordsTable = "CREATE TABLE IF NOT EXISTS borrow_records (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT NOT NULL, " +
                    "book_copy_id INT NOT NULL, " +
                    "borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "return_date TIMESTAMP NULL, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (book_copy_id) REFERENCES book_copies(id) ON DELETE CASCADE);";

            stmt.execute(usersTable);
            stmt.execute(authorsTable);
            stmt.execute(publishersTable);
            stmt.execute(booksTable);
            stmt.execute(book_copies);
            stmt.execute(borrowRecordsTable);

            logger.info("Database tables initialized successfully.");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error initializing database: {0}", e.getMessage());
        }
    }
}
