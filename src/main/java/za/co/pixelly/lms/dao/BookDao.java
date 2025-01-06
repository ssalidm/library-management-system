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
import za.co.pixelly.lms.model.Book;

public class BookDao {
    private static final Logger logger = Logger.getLogger(BookDao.class.getName());

    public void saveBook(Book book) {
        String sql = "INSERT INTO books (isbn, title, author_id, publisher_id, year_published, description)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.isbn());
            pstmt.setString(2, book.title());
            pstmt.setInt(3, book.authorId());
            pstmt.setInt(4, book.publisherId());
            pstmt.setInt(5, book.yearPublished());
            pstmt.setString(6, book.description());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving book: {0}", e);
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                int authorId = rs.getInt("author_id");
                int publisherId = rs.getInt("publisher_id");
                int yearPublished = rs.getInt("year_published");
                String description = rs.getString("description");

                books.add(new Book(isbn, title, authorId, publisherId, yearPublished, description));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving books: {0}", e);
        }
        return books;
    }

    public Book getBookByIsbn(String isbn) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE isbn = ?";

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, isbn);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    int authorId = rs.getInt("author_id");
                    int publisherId = rs.getInt("publisher_id");
                    int yearPublished = rs.getInt("year_published");
                    String description = rs.getString("description");

                    book = new Book(isbn, title, authorId, publisherId, yearPublished, description);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving book: {0}", e);
        }
        return book;
    }

    public int getTotalNumberOfBooks() {
        String sql = "SELECT COUNT(*) AS total_books FROM books";
        int totalBooks = 0;

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalBooks = rs.getInt("total_books");
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving total number of books: {0}", e);
        }

        return totalBooks;
    }

}
