package practice.concurrency;

public class BankAccount {
    private final int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
