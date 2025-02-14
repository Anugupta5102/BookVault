package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import entities.Author;

public class AuthorService {
    public void addAuthor(int adminId, String adminName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Author Name:");
        String authorName = scanner.nextLine();

        String query = "INSERT INTO author (name) VALUES (?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, authorName);
            ps.executeUpdate();
            System.out.println("Author added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAuthors(int adminId, String adminName) {
        System.out.println("Displaying all authors...");
        // Implement query to fetch and display authors
    }
}
