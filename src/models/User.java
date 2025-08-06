package models;

public class User {
    private String userName;
    private String password;
    private String fullName;
    private Account account;

    public User(String userName, String password, String fullName, Account account) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.account = account;
    }

    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public Account getAccount() { return account; }
}
