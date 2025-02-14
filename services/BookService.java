package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import entities.Book;

public class BookService {
    public void addBook(int adminId, String adminName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book Title:");
        String bookTitle = scanner.nextLine();
        System.out.println("Enter Author ID:");
        int authorId = scanner.nextInt();

        String query = "INSERT INTO book (title, author_id) VALUES (?, ?)";
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, bookTitle);
            ps.setInt(2, authorId);
            ps.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adminViewBooks(int adminId, String adminName) {
        System.out.println("Displaying all books...");
        // Implement query to fetch and display books
    }
}
