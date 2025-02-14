package entities;

public class User {
    private int userId;
    private String name;
    private String email;
    private boolean isAdmin;

    public User(int userId, String name, String email, boolean isAdmin) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }
}
