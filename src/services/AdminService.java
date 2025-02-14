package services;

import entities.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminService extends Admin {

    private static final Scanner scanner = new Scanner(System.in);

    public void adminLogin() {
        System.out.println("________________________________________________________________________________________________________________");
        System.out.println("---------------------------");
        System.out.println("|       WELCOME TO        |");
        System.out.println("|    LIBRARY MANAGEMENT   |");
        System.out.println("|         SYSTEM          |");
        System.out.println("|      Admin  Login       |");
        System.out.println("---------------------------");
    
        while (true) {
            try (Connection con = ConnectionClass.getConnectionMethod();
                 PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE LOWER(name) = LOWER(?) AND password = ? AND is_admin = 1")) {
    
                System.out.print("Enter Username: ");
                String username = scanner.next();
                System.out.print("Enter Password: ");
                String password = scanner.next();
    
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
    
                if (rs.next()) {
                    int adminId = rs.getInt("user_id");  // Changed from `adminid` to `user_id`
                    String adminName = rs.getString("name");
                    System.out.println("Login Success.");
                    adminHome(adminId, adminName);
                    break;
                } else {
                    System.out.println("Invalid Username or Password.");
                    System.out.println("1. Try again | 2. Exit");
                    int option = scanner.nextInt();
                    if (option == 2) {
                        new OtherServices().indexMethod();
                        break;
                    }
                }
            } catch (SQLException | InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }
    

    public void adminHome(int adminId, String adminName) {
        System.out.println("________________________________________________________________________________________________________________");
        System.out.println("---------------------------");
        System.out.println("|        Admin Dashboard       |");
        System.out.println("---------------------------");
        System.out.println("Welcome, " + adminName);
        System.out.println("---------------------------");
    
        String userCountQuery = "SELECT COUNT(user_id) FROM users";  
        String bookCountQuery = "SELECT COUNT(book_id) FROM books";  
        String languageCountQuery = "SELECT COUNT(language_id) FROM languages";  
        String authorCountQuery = "SELECT COUNT(author_id) FROM authors";  
    
        try (Connection con = ConnectionClass.getConnectionMethod();
             PreparedStatement psUser = con.prepareStatement(userCountQuery);
             PreparedStatement psBook = con.prepareStatement(bookCountQuery);
             PreparedStatement psLanguage = con.prepareStatement(languageCountQuery);
             PreparedStatement psAuthor = con.prepareStatement(authorCountQuery)) {
    
            ResultSet rsUser = psUser.executeQuery();
            ResultSet rsBook = psBook.executeQuery();
            ResultSet rsLanguage = psLanguage.executeQuery();
            ResultSet rsAuthor = psAuthor.executeQuery();
    
            rsUser.next();
            rsBook.next();
            rsLanguage.next();
            rsAuthor.next();
    
            System.out.println("Total Users    : " + rsUser.getInt(1));
            System.out.println("Total Books    : " + rsBook.getInt(1));
            System.out.println("Total Languages: " + rsLanguage.getInt(1));
            System.out.println("Total Authors  : " + rsAuthor.getInt(1));
            System.out.println("---------------------------");
    
            while (true) {
                System.out.println("|1. Add Books          |");
                System.out.println("|2. Add Authors        |");
                System.out.println("|3. Add Languages      |");
                System.out.println("|4. View Books         |");
                System.out.println("|5. View Authors       |");
                System.out.println("|6. View Languages     |");
                System.out.println("|7. View Due Date List |");
                System.out.println("|8. Logout             |");
                System.out.println("---------------------------");
    
                System.out.print("Enter an Option: ");
                try {
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1 -> new BookService().addBook();
                        case 2 -> new AuthorService().addAuthor(adminId, adminName);
                        case 3 -> new LanguageService().addLanguage(adminId, adminName);
                        case 4 -> new BookService().adminViewBooks(adminId, adminName);
                        case 5 -> new AuthorService().viewAuthors();
                        case 6 -> new LanguageService().viewLanguages();
                        case 7 -> new BookDueListService().getOverDueDateList(adminId, adminName);
                        case 8 -> {
                            new OtherServices().indexMethod();
                            return;
                        }
                        default -> System.out.println("Invalid option! Please select a valid option.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Clear buffer
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
}    