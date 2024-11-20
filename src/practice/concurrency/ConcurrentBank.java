package practice.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private final java.util.Map<Integer, BankAccount> accounts = new java.util.HashMap<>();
    private final Lock bankLock = new ReentrantLock();


    public synchronized BankAccount createAccount(double initialBalance) {
        int accountNumber = accounts.size() + 1;
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        BankAccount first = from.getAccountNumber() < to.getAccountNumber() ? from : to;
        BankAccount second = from.getAccountNumber() < to.getAccountNumber() ? to : from;

        first.deposit(0);
        second.deposit(0);

        synchronized (first) {
            synchronized (second) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                } else {
                    System.out.println("Transfer failed: insufficient funds");
                }
            }
        }
    }

    public synchronized double getTotalBalance() {
        double total = 0;
        for (BankAccount account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }
}
