package models;

public class User {
    private String userName;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String aadharNumber;
    private String panNumber;
    private String address;
    private Account account;

    public User(String userName, String password, String fullName,
                String phoneNumber, String aadharNumber, String panNumber,
                String address, Account account) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.aadharNumber = aadharNumber;
        this.panNumber = panNumber;
        this.address = address;
        this.account = account;
    }

    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public Account getAccount() { return account; }
}
