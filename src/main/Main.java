package main;

import java.util.Scanner;
import services.AdminService;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        AdminService adminService = new AdminService();
        
        while (true) {
            System.out.println("\n📚 Welcome to the Library Management System 📚");
            System.out.println("1️⃣ Admin Login");
            System.out.println("2️⃣ User Login");
            System.out.println("3️⃣ Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {  // Input validation
                System.out.println("⚠️ Invalid input! Please enter a number.");
                scanner.next(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminService.adminLogin();
                    break;
                case 2:
                    userService.userLogin();
                    break;
                case 3:
                    System.out.println("👋 Exiting the system...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter a valid option.");
            }
        }
    }
}
