package za.co.pixelly.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.pixelly.jdbc.config.DatabaseConfig;
import za.co.pixelly.lms.model.BookCopy;

public class BookCopyDao {
    private static final Logger logger = Logger.getLogger(BookCopyDao.class.getName());

    public void saveCopy(BookCopy bookCopy) {
        String sql = "INSERT INTO book_copies (isbn, is_available) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookCopy.isbn());
            stmt.setBoolean(2, bookCopy.isAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving book copy: {0}", e.getMessage());
        }
    }

    public List<BookCopy> getAllCopies() {
        String sql = "SELECT * FROM book_copies";
        List<BookCopy> copies = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                copies.add(new BookCopy(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getBoolean("is_available")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving book copies: {0}", e.getMessage());
        }
        return copies;
    }

    public int getNumberOfCopies(String isbn) {
        String sql = "SELECT COUNT(*) AS total_copies FROM book_copies WHERE isbn = ?";
        int totalCopies = 0;

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    totalCopies = rs.getInt("total_copies");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving total number of copies: {0}", e.getMessage());
        }
        return totalCopies;
    }

    public int getAvailableCopies(String isbn) {
        String sql = "SELECT COUNT(*) AS available_copies FROM book_copies WHERE isbn = ? AND is_available = TRUE";
        int availableCopies = 0;

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    availableCopies = rs.getInt("available_copies");
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving total number of available copies: {0}", e.getMessage());
        }

        return availableCopies;
    }

}
