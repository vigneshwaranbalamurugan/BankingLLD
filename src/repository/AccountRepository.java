package repository;

import models.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accNumber) {
        return accounts.get(accNumber);
    }
}
