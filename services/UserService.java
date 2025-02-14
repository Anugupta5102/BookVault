package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import entities.User;

public class UserService {
    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter User Name:");
        String userName = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.next();

        String query = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewUsers() {
        System.out.println("Displaying all users...");
        // Implement query to fetch and display users
    }
}
