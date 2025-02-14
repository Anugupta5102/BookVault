package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import entities.Language;

public class LanguageService {
    public void addLanguage(int adminId, String adminName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Language Name:");
        String languageName = scanner.nextLine();

        String query = "INSERT INTO language (name) VALUES (?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, languageName);
            ps.executeUpdate();
            System.out.println("Language added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewLanguages(int adminId, String adminName) {
        System.out.println("Displaying all languages...");
        // Implement query to fetch and display languages
    }
}
