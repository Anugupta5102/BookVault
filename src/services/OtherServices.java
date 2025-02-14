package services;
import java.util.Scanner;

public class OtherServices {
    public void indexMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Admin Login");
        System.out.println("2. User Registration");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
        
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                AdminService adminService = new AdminService();
                try {
                    adminService.adminLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                UserService userService = new UserService();
                userService.registerUser();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option!");
        }
    }
}
