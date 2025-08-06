package models;

public class Admin {
    private final String adminUsername;
    private final String adminPassword;

    public Admin(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public String getAdminUsername() { return adminUsername; }
    public String getAdminPassword() { return adminPassword; }
}
