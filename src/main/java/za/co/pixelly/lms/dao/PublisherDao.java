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
import za.co.pixelly.lms.model.Publisher;

public class PublisherDao {
    private static final Logger logger = Logger.getLogger(PublisherDao.class.getName());

    public void savePublisher(Publisher Publisher) {
        String sql = "INSERT INTO publishers (name) VALUES (?);";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Publisher.name());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving Publisher: {0}", e.getMessage());
        }
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        String sql = "SELECT * FROM publishers";

        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                publishers.add(new Publisher(id, name));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching publishers: {0}", e.getMessage());
        }
        return publishers;
    }

    public Publisher getPublisherById(int publisherId) {
        Publisher publisher = null;
        String sql = "SELECT * FROM publishers WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, publisherId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    publisher = new Publisher(id, name);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching Publisher: {0}", e.getMessage());
        }

        return publisher;
    }

    public boolean updatePublisher(int publisherId, Publisher updatedPublisher) {
        boolean isUpdated = false;
        String sql = "UPDATE publishers SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, updatedPublisher.name());
            pstmt.setInt(2, publisherId);

            int affectedRows = pstmt.executeUpdate();
            isUpdated = affectedRows > 0;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating Publisher: {0}", e.getMessage());
        }

        return isUpdated;
    }

    public boolean deletePublisher(int publisherId) {
        boolean isDeleted = false;
        String sql = "DELETE FROM publishers WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, publisherId);
            isDeleted = pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting Publisher: {0}", e.getMessage());
        }
        return isDeleted;
    }
}
