package defaultPackage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Library Management System");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            AdminService.adminMenu(scanner);
        } else if (choice == 2) {
            UserService.userMenu(scanner);
        } else {
            System.out.println("Invalid option! Exiting...");
        }
        scanner.close();
    }
}
