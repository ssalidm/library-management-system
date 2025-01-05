package za.co.pixelly.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.pixelly.jdbc.config.DatabaseConfig;
import za.co.pixelly.lms.model.Author;

public class AuthorDao {
    private static final Logger logger = Logger.getLogger(AuthorDao.class.getName());

    public void saveAuthor(Author author) {
        String sql = "INSERT INTO authors (name) VALUES (?);";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving author: {0}", e.getMessage());
        }
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM authors";

        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                authors.add(new Author(id, name));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching authors: {0}", e.getMessage());
        }
        return authors;
    }

    public Author getAuthorById(int authorId) {
        Author author = null;
        String sql = "SELECT * FROM authors WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, authorId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    author = new Author(id, name);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching author: {0}", e.getMessage());
        }

        return author;
    }

    public boolean updateAuthor(int authorId, Author updatedAuthor) {
        boolean isUpdated = false;
        String sql = "UPDATE authors SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, updatedAuthor.name());
            pstmt.setInt(2, authorId);

            int affectedRows = pstmt.executeUpdate();
            isUpdated = affectedRows > 0;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating author: {0}", e.getMessage());
        }

        return isUpdated;
    }

    public boolean deleteAuthor(int authorId) {
        boolean isDeleted = false;
        String sql = "DELETE FROM authors WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, authorId);
            isDeleted = pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting author: {0}", e.getMessage());
        }
        return isDeleted;
    }
}
