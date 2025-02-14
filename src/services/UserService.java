package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserService {

    // Method to register a new user
    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User Name:");
        String userName = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.next();
        
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, userName);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            System.out.println("✅ User registered successfully!");
        } catch (SQLException e) {
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
                        rs.getString("email"), rs.getBoolean("is_admin") ? "✅ Yes" : "❌ No");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update user details
    public void updateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User ID to update:");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
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
            e.printStackTrace();
        }
    }

    // Method to delete a user
    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);
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
            e.printStackTrace();
        }
    }

    public void userLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
    
        String query = "SELECT user_id, name, is_admin FROM users WHERE email = ? AND password = ?";
        
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("name");
                boolean isAdmin = rs.getBoolean("is_admin");
    
                System.out.println("✅ Login successful! Welcome, " + userName);
                System.out.println("🔹 User ID: " + userId);
                System.out.println("🔹 Role: " + (isAdmin ? "Admin" : "User"));
            } else {
                System.out.println("❌ Invalid email or password. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
