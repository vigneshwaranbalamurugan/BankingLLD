package services;

import abstractions.IBankService;
import models.Account;
import models.Transaction;
import models.User;
import repository.AccountRepository;
import repository.UserRepository;
import repository.TransactionRepository;

public class BankService implements IBankService {
    private final UserRepository userRepo;
    private final AccountRepository accRepo;
    private final TransactionRepository txRepo;

    public BankService(UserRepository userRepo, AccountRepository accRepo, TransactionRepository txRepo) {
        this.userRepo = userRepo;
        this.accRepo = accRepo;
        this.txRepo = txRepo;
    }

    @Override
    public void registerUser(User user) {
        userRepo.addUser(user);
        accRepo.addAccount(user.getAccount());
        txRepo.addTransaction(new Transaction(
                user.getAccount().getAccountNumber(),
                "Account Created",
                0.0
        ));
    }


    @Override
    public void deposit(String userName, double amount) {
        User user = userRepo.getUser(userName);
        if (user != null) {
            user.getAccount().deposit(amount);
            txRepo.addTransaction(new Transaction(user.getAccount().getAccountNumber(), "Deposit", amount));
            System.out.println("Deposited: ₹" + amount);
        }
    }


    @Override
    public void withdraw(String userName, double amount) {
        User user = userRepo.getUser(userName);
        if (user != null && user.getAccount().withdraw(amount)) {
            txRepo.addTransaction(new Transaction(user.getAccount().getAccountNumber(), "Withdrawal", amount));
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Insufficient balance or user not found.");
        }
    }

    @Override
    public void transfer(String senderUsername, String receiverUsername, double amount) {
        User sender = userRepo.getUser(senderUsername);
        User receiver = userRepo.getUser(receiverUsername);

        if (sender == null || receiver == null) {
            System.out.println("Sender or receiver does not exist.");
            return;
        }

        Account senderAcc = sender.getAccount();
        Account receiverAcc = receiver.getAccount();

        if (senderAcc.withdraw(amount)) {
            receiverAcc.deposit(amount);

            txRepo.addTransaction(new Transaction(
                    senderAcc.getAccountNumber(),
                    "Transferred to " + receiverUsername,
                    amount
            ));

            txRepo.addTransaction(new Transaction(
                    receiverAcc.getAccountNumber(),
                    "Received from " + senderUsername,
                    amount
            ));

            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }


    @Override
    public void showTransactionSummary(String userName) {
        User user = userRepo.getUser(userName);
        if (user != null) {
            String accNo = user.getAccount().getAccountNumber();
            var txns = txRepo.getTransactionsByAccount(accNo);
            if (txns.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("Transaction Summary:");
                txns.forEach(System.out::println);
            }
        }
    }

    @Override
    public void showBalance(String userName) {
        User user = userRepo.getUser(userName);
        if (user != null) {
            System.out.println("Balance: ₹" + user.getAccount().getBalance());
        }
    }
}
