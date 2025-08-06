package repository;

import models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction tx) {
        transactions.add(tx);
    }

    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return transactions.stream()
                .filter(tx -> tx.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList());
    }
}
