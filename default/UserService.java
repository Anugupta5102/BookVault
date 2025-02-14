package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    
    // Register a new user
    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Full Name:");
        String fullName = scanner.nextLine();
        System.out.println("Enter PIN (4-digit number):");
        String pin = scanner.next();

        // Validate PIN
        if (!pin.matches("\\d{4}")) {
            System.out.println("Invalid PIN! Please enter a 4-digit number.");
            return;
        }

        // Insert user into database
        String query = "INSERT INTO accounts (full_name, balance, pin) VALUES (?, ?, ?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, fullName);
            ps.setDouble(2, 0.0);  // Default balance = 0
            ps.setString(3, pin);
            ps.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all users
    public void viewUsers() {
        String query = "SELECT acc_no, full_name, balance FROM accounts";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            System.out.println("User List:");
            System.out.println("------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s\n", "Acc No", "Full Name", "Balance");
            System.out.println("------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-20s %-10.2f\n", 
                                  rs.getInt("acc_no"), 
                                  rs.getString("full_name"), 
                                  rs.getDouble("balance"));
            }
            System.out.println("------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // User login
    public boolean userLogin(int accNo, String pin) {
        String query = "SELECT * FROM accounts WHERE acc_no = ? AND pin = ?";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, accNo);
            ps.setString(2, pin);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Login successful! Welcome, " + rs.getString("full_name"));
                    return true;
                } else {
                    System.out.println("Invalid account number or PIN.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a user
    public void deleteUser(int accNo) {
        String query = "DELETE FROM accounts WHERE acc_no = ?";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, accNo);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with Account No " + accNo + " deleted successfully.");
            } else {
                System.out.println("No user found with Account No " + accNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update user details (Only Full Name and PIN)
    public void updateUser(int accNo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Full Name:");
        String newFullName = scanner.nextLine();
        System.out.println("Enter new PIN (4-digit number):");
        String newPin = scanner.next();

        if (!newPin.matches("\\d{4}")) {
            System.out.println("Invalid PIN! Please enter a 4-digit number.");
            return;
        }

        String query = "UPDATE accounts SET full_name = ?, pin = ? WHERE acc_no = ?";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, newFullName);
            ps.setString(2, newPin);
            ps.setInt(3, accNo);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User details updated successfully!");
            } else {
                System.out.println("No user found with Account No " + accNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
