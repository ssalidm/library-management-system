package za.co.pixelly.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import za.co.pixelly.jdbc.config.DatabaseConfig;
import za.co.pixelly.lms.model.User;

public class UserDao {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    public void saveUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());

            pstmt.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            logger.log(Level.SEVERE, "Duplicate email: {0}", e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error saving user: {0}", e.getMessage());
        }
    }

    public List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                users.add(new User(id, firstName, lastName, email));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to fetch users: {0}", e.getMessage());
        }
        return users;
    }

    public User getUserById(int id) throws Exception {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(id, rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to fetch user bu ID: {0}", e.getMessage());
        }
        return null;
    }

    public boolean updateUser(int id, User updatedUser) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        boolean isUpdated = false;

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedUser.getFirstName());
            pstmt.setString(2, updatedUser.getLastName());
            pstmt.setString(3, updatedUser.getEmail());
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();
            isUpdated = rowsAffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating user: {0}", e.getMessage());
        }
        return isUpdated;
    }

    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        boolean isDeleted = false;

        try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            isDeleted = affectedRows > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting user: {0}", e.getMessage());
        }
        return isDeleted;
    }
}
