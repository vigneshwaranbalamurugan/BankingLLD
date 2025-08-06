package models;

public class Admin {
    private final String adminUsername;
    private final String adminPassword;
    private static Admin instance = null;

    private Admin(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin", "admin123");
        }
        return instance;
    }

    public String getAdminUsername() { return adminUsername; }
    public String getAdminPassword() { return adminPassword; }
}
