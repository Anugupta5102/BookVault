package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RatingService {
    public void rateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book ID:");
        int bookId = scanner.nextInt();
        System.out.println("Enter Rating (1-5):");
        int rating = scanner.nextInt();

        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating! Must be between 1 and 5.");
            return;
        }

        String query = "INSERT INTO rating (book_id, rating) VALUES (?, ?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.setInt(2, rating);
            ps.executeUpdate();
            System.out.println("Rating submitted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
