package abstractions;

import models.User;

public interface IBankService {
    void registerUser(User user);
    void deposit(String userName, double amount);
    void withdraw(String userName, double amount);
    void showBalance(String userName);
    void showTransactionSummary(String userName);
    void transfer(String senderUsername, String receiverUsername, double amount);
}
