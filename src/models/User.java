package models;

public class User extends  Person {
    private String fullName;
    private Account account;

    public User(String userName, String password, String fullName, Account account) {
        super(userName,password);
        this.fullName = fullName;
        this.account = account;
    }

    public Account getAccount() { return account; }
}

