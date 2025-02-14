package entities;

public class Admin {
    private int adminId;
    private String name;
    private String email;
    private String password;

    public Admin(int adminId, String name, String email, String password) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
