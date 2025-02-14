package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RatingService {

    public void rateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter Rating (1-5): ");
        int rating = scanner.nextInt();

        if (rating < 1 || rating > 5) {
            System.out.println("❌ Invalid rating! Must be between 1 and 5.");
            return;
        }

        String checkQuery = "SELECT * FROM books WHERE book_id = ?";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Book ID not found! Please enter a valid book.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Insert rating only if book exists
        String insertQuery = "INSERT INTO rating (book_id, rating) VALUES (?, ?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setInt(1, bookId);
            ps.setInt(2, rating);
            ps.executeUpdate();
            System.out.println("✅ Rating submitted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
