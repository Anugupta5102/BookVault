package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt; // Hashing library


public class UserService {
    private final Scanner scanner = new Scanner(System.in); // Class-level scanner to prevent leaks

    // Method to register a new user
    public void registerUser() {
        System.out.println("Enter User Name:");
        String userName = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.next();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt()); // Hash password

        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);
            ps.executeUpdate();
            System.out.println("✅ User registered successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Error: Unable to register user.");
            e.printStackTrace();
        }
    }

    // User Login with Secure Password Checking
    public void userLogin() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String query = "SELECT user_id, name, is_admin, password FROM users WHERE email = ?";

        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                if (BCrypt.checkpw(password, storedHash)) { // Compare hashed password
                    int userId = rs.getInt("user_id");
                    String userName = rs.getString("name");
                    boolean isAdmin = rs.getInt("is_admin") == 1; // Ensure correct boolean handling

                    System.out.println("✅ Login successful! Welcome, " + userName);
                    System.out.println("🔹 User ID: " + userId);
                    System.out.println("🔹 Role: " + (isAdmin ? "Admin" : "User"));
                } else {
                    System.out.println("❌ Incorrect password. Try again.");
                }
            } else {
                System.out.println("❌ No user found with this email.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: Unable to log in.");
            e.printStackTrace();
        }
    }

    // Method to view all registered users
    public void viewUsers() {
        String query = "SELECT user_id, name, email, is_admin FROM users";

        try (Connection con = ConnectionClass.getConnectionMethod();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n👥 Registered Users:");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Email: %s | Admin: %s\n",
                        rs.getInt("user_id"), rs.getString("name"),
                        rs.getString("email"), rs.getInt("is_admin") == 1 ? "✅ Yes" : "❌ No");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: Unable to retrieve users.");
            e.printStackTrace();
        }
    }

    // Method to update user details
    public void updateUser() {
        System.out.println("Enter User ID to update:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new Name:");
        String newName = scanner.nextLine();
        System.out.println("Enter new Email:");
        String newEmail = scanner.nextLine();

        String query = "UPDATE users SET name = ?, email = ? WHERE user_id = ?";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, newName);
            ps.setString(2, newEmail);
            ps.setInt(3, userId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ User details updated successfully!");
            } else {
                System.out.println("⚠️ No user found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: Unable to update user.");
            e.printStackTrace();
        }
    }

    // Method to delete a user
    public void deleteUser() {
        System.out.println("Enter User ID to delete:");
        int userId = scanner.nextInt();

        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ User deleted successfully!");
            } else {
                System.out.println("⚠️ No user found with the given ID.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: Unable to delete user.");
            e.printStackTrace();
        }
    }
}
